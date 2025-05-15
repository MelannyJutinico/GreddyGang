CREATE OR ALTER PROCEDURE sp_generar_provisiones
    @pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @pn_id_empleado INT;
    DECLARE @vn_id_nomina INT;
    DECLARE @vn_salario_mes DECIMAL(18,2);
    DECLARE @vn_id_tipo_contrato INT;
    DECLARE @vn_base_cotizacion DECIMAL(18,2);
    DECLARE @vn_salario_integral DECIMAL(18,2);

    DECLARE @vn_prima DECIMAL(18,2);
    DECLARE @vn_cesantias DECIMAL(18,2);
    DECLARE @vn_intereses DECIMAL(18,2);
    DECLARE @vn_vacaciones DECIMAL(18,2);

    DECLARE @vn_dias_trabajados INT;
    DECLARE @vn_id_concepto_sueldo INT;

    DECLARE @vn_total_generados INT = 0;

    -- Obtener salario integral referencia
    SELECT @vn_salario_integral = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'SALARIO_INTEGRAL' AND activo = 1;

    -- ID concepto sueldo base
    SELECT @vn_id_concepto_sueldo = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Sueldo Base' AND activo = 1;

    DECLARE nomina_cursor CURSOR FOR
        SELECT n.id_nomina, e.id_empleado, e.id_tipo_contrato
        FROM nomina n
        INNER JOIN empleado e ON n.id_empleado = e.id_empleado
        WHERE n.id_periodo = @pn_id_periodo
          AND n.estado IN ('GENERADA', 'EN_PROCESO')
          AND e.activo = 1;

    OPEN nomina_cursor;
    FETCH NEXT FROM nomina_cursor INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        IF @vn_id_tipo_contrato <> 4
        BEGIN
            SELECT @vn_salario_mes = valor_total
            FROM detalle_nomina
            WHERE id_nomina = @vn_id_nomina
              AND id_concepto = @vn_id_concepto_sueldo;

            IF @vn_salario_mes IS NOT NULL
            BEGIN
                -- Obtener días reales trabajados
                SELECT @vn_dias_trabajados = dbo.fn_dias_trabajados(@pn_id_periodo, @pn_id_empleado);
                IF @vn_dias_trabajados IS NULL OR @vn_dias_trabajados <= 0 SET @vn_dias_trabajados = 0;

                IF @vn_salario_mes >= @vn_salario_integral
                    SET @vn_base_cotizacion = @vn_salario_mes * 0.7;
                ELSE
                    SET @vn_base_cotizacion = @vn_salario_mes;

                SET @vn_prima = 0;
                SET @vn_cesantias = 0;
                SET @vn_intereses = 0;
                SET @vn_vacaciones = 0;

                SET @vn_cesantias = (@vn_base_cotizacion * @vn_dias_trabajados) / 360.0;
                SET @vn_intereses = (@vn_cesantias * 0.12) / 12.0;

                IF @vn_salario_mes < @vn_salario_integral
                BEGIN
                    SET @vn_prima = (@vn_base_cotizacion * @vn_dias_trabajados) / 360.0;
                    SET @vn_vacaciones = (@vn_base_cotizacion * @vn_dias_trabajados) / 720.0;
                END

                IF @vn_prima > 0
                BEGIN
                    INSERT INTO provision_prestaciones(id_empleado, id_periodo, id_concepto_provision, cantidad)
                    VALUES(@pn_id_empleado, @pn_id_periodo,
                        (SELECT id_concepto_provision FROM concepto_provision WHERE nombre = 'Prima de Servicios' AND activo = 1),
                        ROUND(@vn_prima, 2));
                    SET @vn_total_generados += 1;
                END

                IF @vn_cesantias > 0
                BEGIN
                    INSERT INTO provision_prestaciones(id_empleado, id_periodo, id_concepto_provision, cantidad)
                    VALUES(@pn_id_empleado, @pn_id_periodo,
                        (SELECT id_concepto_provision FROM concepto_provision WHERE nombre = 'Cesantías' AND activo = 1),
                        ROUND(@vn_cesantias, 2));
                    SET @vn_total_generados += 1;
                END

                IF @vn_intereses > 0
                BEGIN
                    INSERT INTO provision_prestaciones(id_empleado, id_periodo, id_concepto_provision, cantidad)
                    VALUES(@pn_id_empleado, @pn_id_periodo,
                        (SELECT id_concepto_provision FROM concepto_provision WHERE nombre = 'Intereses sobre Cesantías' AND activo = 1),
                        ROUND(@vn_intereses, 2));
                    SET @vn_total_generados += 1;
                END

                IF @vn_vacaciones > 0
                BEGIN
                    INSERT INTO provision_prestaciones(id_empleado, id_periodo, id_concepto_provision, cantidad)
                    VALUES(@pn_id_empleado, @pn_id_periodo,
                        (SELECT id_concepto_provision FROM concepto_provision WHERE nombre = 'Vacaciones' AND activo = 1),
                        ROUND(@vn_vacaciones, 2));
                    SET @vn_total_generados += 1;
                END
            END
        END
        FETCH NEXT FROM nomina_cursor INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;
    END

    CLOSE nomina_cursor;
    DEALLOCATE nomina_cursor;

    IF @vn_total_generados = 0
    BEGIN
        THROW 51000, 'No se generaron provisiones. Verifique si los empleados tienen sueldo base registrado.', 1;
    END

    SELECT 'OK' AS estado, 'Provisiones generadas exitosamente.' AS mensaje;
END;