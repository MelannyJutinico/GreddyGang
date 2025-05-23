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

    DECLARE @vn_dias_trabajados INT = 30; -- Por ahora asumimos 30 días completos

    DECLARE @vn_id_concepto_sueldo INT;

    -- Obtener parámetro salario integral
    SELECT @vn_salario_integral = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'SALARIO_INTEGRAL' AND activo = 1;

    -- Obtener ID del concepto "Sueldo Base"
    SELECT @vn_id_concepto_sueldo = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Sueldo Base' AND activo = 1;

    -- Cursor para recorrer nóminas activas del periodo
    DECLARE empleados_cursor CURSOR FOR
        SELECT 
            n.id_nomina,
            e.id_empleado,
            e.id_tipo_contrato
        FROM nomina n
        INNER JOIN empleado e ON n.id_empleado = e.id_empleado
        WHERE n.id_periodo = @pn_id_periodo
          AND n.estado IN ('GENERADA', 'EN_PROCESO')
          AND e.activo = 1;

    OPEN empleados_cursor;
    FETCH NEXT FROM empleados_cursor INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Solo generar provisiones si NO es prestación de servicios
        IF @vn_id_tipo_contrato <> 4
        BEGIN
            -- Traer salario del mes real (ajustado si hubo novedades)
            SELECT @vn_salario_mes = valor_total
            FROM detalle_nomina
            WHERE id_nomina = @vn_id_nomina
              AND id_concepto = @vn_id_concepto_sueldo;

            IF @vn_salario_mes IS NOT NULL
            BEGIN
                -- Determinar base de cotización
                IF @vn_salario_mes >= @vn_salario_integral
                BEGIN
                    SET @vn_base_cotizacion = @vn_salario_mes * 0.7; -- 70% si salario integral
                END
                ELSE
                BEGIN
                    SET @vn_base_cotizacion = @vn_salario_mes; -- 100% si no integral
                END

                -- Calcular provisiones
                SET @vn_prima = 0;
                SET @vn_cesantias = 0;
                SET @vn_intereses = 0;
                SET @vn_vacaciones = 0;

                -- Cesantías: siempre
                SET @vn_cesantias = (@vn_base_cotizacion * @vn_dias_trabajados) / 360;

                -- Intereses sobre cesantías
                SET @vn_intereses = (@vn_cesantias * 0.12) / 12; -- 1% mensual

                -- Prima y vacaciones solo si NO es salario integral
                IF @vn_salario_mes < @vn_salario_integral
                BEGIN
                    -- Prima
                    SET @vn_prima = (@vn_base_cotizacion * @vn_dias_trabajados) / 360;

                    -- Vacaciones
                    SET @vn_vacaciones = (@vn_base_cotizacion * @vn_dias_trabajados) / 720;
                END

                -- Insertar Provisiones
                IF @vn_prima > 0
                BEGIN
                    INSERT INTO provision_prestaciones (
                        id_empleado,
                        id_periodo,
                        id_concepto_provision,
                        cantidad
                    )
                    VALUES (
                        @pn_id_empleado,
                        @pn_id_periodo,
                        (SELECT id_concepto_provision FROM concepto_provision WHERE nombre = 'Prima de Servicios' AND activo = 1),
                        ROUND(@vn_prima, 2)
                    );
                END

                IF @vn_cesantias > 0
                BEGIN
                    INSERT INTO provision_prestaciones (
                        id_empleado,
                        id_periodo,
                        id_concepto_provision,
                        cantidad
                    )
                    VALUES (
                        @pn_id_empleado,
                        @pn_id_periodo,
                        (SELECT id_concepto_provision FROM concepto_provision WHERE nombre = 'Cesantías' AND activo = 1),
                        ROUND(@vn_cesantias, 2)
                    );
                END

                IF @vn_intereses > 0
                BEGIN
                    INSERT INTO provision_prestaciones (
                        id_empleado,
                        id_periodo,
                        id_concepto_provision,
                        cantidad
                    )
                    VALUES (
                        @pn_id_empleado,
                        @pn_id_periodo,
                        (SELECT id_concepto_provision FROM concepto_provision WHERE nombre = 'Intereses sobre Cesantías' AND activo = 1),
                        ROUND(@vn_intereses, 2)
                    );
                END

                IF @vn_vacaciones > 0
                BEGIN
                    INSERT INTO provision_prestaciones (
                        id_empleado,
                        id_periodo,
                        id_concepto_provision,
                        cantidad
                    )
                    VALUES (
                        @pn_id_empleado,
                        @pn_id_periodo,
                        (SELECT id_concepto_provision FROM concepto_provision WHERE nombre = 'Vacaciones' AND activo = 1),
                        ROUND(@vn_vacaciones, 2)
                    );
                END
            END
        END

        FETCH NEXT FROM empleados_cursor INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;
    END

    CLOSE empleados_cursor;
    DEALLOCATE empleados_cursor;

    SELECT 'OK' AS estado, 'Provisiones generadas exitosamente.' AS mensaje;
END