CREATE OR ALTER PROCEDURE sp_generar_deducciones_automaticas
    @pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @pn_id_empleado INT;
    DECLARE @vn_id_nomina INT;
    DECLARE @vn_salario_mes DECIMAL(18,2);
    DECLARE @vn_id_tipo_contrato INT;
    DECLARE @vn_base_cotizacion DECIMAL(18,2);

    DECLARE @vn_smmlv DECIMAL(18,2);
    DECLARE @vn_salario_integral DECIMAL(18,2);

    DECLARE @vn_porcentaje_salud DECIMAL(5,2);
    DECLARE @vn_porcentaje_pension DECIMAL(5,2);
    DECLARE @vn_porcentaje_fondo DECIMAL(5,2);

    DECLARE @vn_id_concepto_salud INT;
    DECLARE @vn_id_concepto_pension INT;
    DECLARE @vn_id_concepto_fondo INT;
    DECLARE @vn_id_concepto_sueldo INT;

    -- Obtener parámetros de nómina
    SELECT @vn_smmlv = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'SALARIO_MINIMO' AND activo = 1;

    SELECT @vn_salario_integral = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'SALARIO_INTEGRAL' AND activo = 1;

    -- Obtener IDs de conceptos
    SELECT @vn_id_concepto_sueldo = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Sueldo Base' AND activo = 1;

    SELECT 
        @vn_porcentaje_salud = porcentaje,
        @vn_id_concepto_salud = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Salud' AND activo = 1;

    SELECT 
        @vn_porcentaje_pension = porcentaje,
        @vn_id_concepto_pension = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Pensión' AND activo = 1;

    SELECT 
        @vn_porcentaje_fondo = porcentaje,
        @vn_id_concepto_fondo = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Fondo de Solidaridad' AND activo = 1;

    -- Cursor para recorrer nóminas de empleados en ese periodo
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
        -- Solo si NO es prestación de servicios
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
                    SET @vn_base_cotizacion = @vn_salario_mes * 0.7; -- 70% si es salario integral
                END
                ELSE
                BEGIN
                    SET @vn_base_cotizacion = @vn_salario_mes; -- 100% si no es integral
                END

                -- Insertar deducción de Salud
                INSERT INTO detalle_nomina (
                    id_nomina,
                    id_concepto,
                    cantidad,
                    valor_unitario,
                    valor_total
                )
                VALUES (
                    @vn_id_nomina,
                    @vn_id_concepto_salud,
                    NULL,
                    NULL,
                    ROUND(@vn_base_cotizacion * @vn_porcentaje_salud / 100, 2)
                );

                -- Insertar deducción de Pensión
                INSERT INTO detalle_nomina (
                    id_nomina,
                    id_concepto,
                    cantidad,
                    valor_unitario,
                    valor_total
                )
                VALUES (
                    @vn_id_nomina,
                    @vn_id_concepto_pension,
                    NULL,
                    NULL,
                    ROUND(@vn_base_cotizacion * @vn_porcentaje_pension / 100, 2)
                );

                -- Validar si aplica fondo de solidaridad
                IF @vn_salario_mes > (@vn_smmlv * 4) AND @vn_salario_mes < @vn_salario_integral
                BEGIN
                    INSERT INTO detalle_nomina (
                        id_nomina,
                        id_concepto,
                        cantidad,
                        valor_unitario,
                        valor_total
                    )
                    VALUES (
                        @vn_id_nomina,
                        @vn_id_concepto_fondo,
                        NULL,
                        NULL,
                        ROUND(@vn_salario_mes * @vn_porcentaje_fondo / 100, 2)
                    );
                END
            END
        END

        FETCH NEXT FROM empleados_cursor INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;
    END

    CLOSE empleados_cursor;
    DEALLOCATE empleados_cursor;

    SELECT 'OK' AS estado, 'Deducciones automáticas generadas exitosamente.' AS mensaje;
END