CREATE OR ALTER PROCEDURE sp_generar_aportes_patronales
    @pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @pn_id_empleado INT;
    DECLARE @vn_id_nomina INT;
    DECLARE @vn_salario_mes DECIMAL(18,2);
    DECLARE @vn_id_tipo_contrato INT;
    DECLARE @vn_nivel_riesgo INT;
    DECLARE @vn_base_cotizacion DECIMAL(18,2);

    DECLARE @vn_salario_integral DECIMAL(18,2);

    DECLARE @vn_id_tipo_aporte INT;
    DECLARE @vv_nombre_aporte VARCHAR(100);
    DECLARE @vn_porcentaje DECIMAL(5,2);
    DECLARE @vn_cantidad_aporte DECIMAL(18,2);

    DECLARE @vn_porcentaje_arl DECIMAL(5,3);

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
            e.id_tipo_contrato,
            e.nivel_riesgo
        FROM nomina n
        INNER JOIN empleado e ON n.id_empleado = e.id_empleado
        WHERE n.id_periodo = @pn_id_periodo
          AND n.estado IN ('GENERADA', 'EN_PROCESO')
          AND e.activo = 1;

    OPEN empleados_cursor;
    FETCH NEXT FROM empleados_cursor INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato, @vn_nivel_riesgo;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Solo generar aportes si NO es prestación de servicios
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
                    SET @vn_base_cotizacion = @vn_salario_mes * 0.7; -- 70% para salario integral
                END
                ELSE
                BEGIN
                    SET @vn_base_cotizacion = @vn_salario_mes; -- 100% para salarios normales
                END

                -- Recorrer tipos de aportes patronales
                DECLARE aportes_cursor CURSOR FOR
                    SELECT id_tipo_aporte, nombre, porcentaje
                    FROM tipo_aporte_patronal
                    WHERE activo = 1;

                OPEN aportes_cursor;
                FETCH NEXT FROM aportes_cursor INTO @vn_id_tipo_aporte, @vv_nombre_aporte, @vn_porcentaje;

                WHILE @@FETCH_STATUS = 0
                BEGIN
                    SET @vn_cantidad_aporte = 0;

                    -- Procesar cada tipo de aporte
                    IF @vv_nombre_aporte IN ('Salud Patronal', 'Pensión Patronal')
                    BEGIN
                        -- Siempre aplica
                        SET @vn_cantidad_aporte = @vn_base_cotizacion * @vn_porcentaje / 100;
                    END
                    ELSE IF @vv_nombre_aporte IN ('SENA', 'ICBF', 'Caja de Compensación')
                    BEGIN
                        -- Solo si NO es salario integral
                        IF @vn_salario_mes < @vn_salario_integral
                        BEGIN
                            SET @vn_cantidad_aporte = @vn_salario_mes * @vn_porcentaje / 100;
                        END
                    END
                    ELSE IF @vv_nombre_aporte = 'ARL'
                    BEGIN
                        -- ARL depende del nivel de riesgo
                        SELECT @vn_porcentaje_arl = porcentaje
                        FROM nivel_riesgo_ARL
                        WHERE id_nivel = @vn_nivel_riesgo;

                        SET @vn_cantidad_aporte = @vn_base_cotizacion * @vn_porcentaje_arl / 100;
                    END

                    -- Insertar aporte si corresponde
                    IF @vn_cantidad_aporte > 0
                    BEGIN
                        INSERT INTO aporte_patronal (
                            id_nomina,
                            id_tipo_aporte,
                            cantidad
                        )
                        VALUES (
                            @vn_id_nomina,
                            @vn_id_tipo_aporte,
                            ROUND(@vn_cantidad_aporte, 2)
                        );
                    END

                    FETCH NEXT FROM aportes_cursor INTO @vn_id_tipo_aporte, @vv_nombre_aporte, @vn_porcentaje;
                END

                CLOSE aportes_cursor;
                DEALLOCATE aportes_cursor;
            END
        END

        FETCH NEXT FROM empleados_cursor INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato, @vn_nivel_riesgo;
    END

    CLOSE empleados_cursor;
    DEALLOCATE empleados_cursor;

    SELECT 'OK' AS estado, 'Aportes patronales generados exitosamente.' AS mensaje;
END