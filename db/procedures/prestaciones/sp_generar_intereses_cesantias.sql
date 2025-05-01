CREATE OR ALTER PROCEDURE sp_generar_intereses_cesantias
    @pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @pn_id_empleado INT;
    DECLARE @vn_id_nomina INT;
    DECLARE @vn_salario_mes DECIMAL(18,2);
    DECLARE @vn_salario_integral DECIMAL(18,2);
    DECLARE @vn_id_tipo_contrato INT;
    DECLARE @vn_dias_trabajados INT;
    DECLARE @vn_cesantias DECIMAL(18,2);
    DECLARE @vn_intereses DECIMAL(18,2);

    DECLARE @vn_id_concepto_intereses INT;

    -- Obtener parámetro salario integral
    SELECT @vn_salario_integral = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'SALARIO_INTEGRAL' AND activo = 1;

    -- Obtener ID del concepto intereses sobre cesantías
    SELECT @vn_id_concepto_intereses = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Intereses sobre Cesantías' AND activo = 1;

    -- Cursor para recorrer empleados del periodo
    DECLARE cursor_nominas CURSOR FOR
        SELECT 
            n.id_nomina,
            e.id_empleado,
            e.id_tipo_contrato
        FROM nomina n
        INNER JOIN empleado e ON e.id_empleado = n.id_empleado
        WHERE n.id_periodo = @pn_id_periodo
          AND n.estado IN ('GENERADA', 'EN_PROCESO', 'LIQUIDADA')
          AND e.activo = 1;

    OPEN cursor_nominas;
    FETCH NEXT FROM cursor_nominas INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        IF @vn_id_tipo_contrato <> 4
        BEGIN
            -- Obtener salario real y días trabajados
            SELECT @vn_salario_mes = dbo.fn_salario_mensual_empleado(@pn_id_periodo, @pn_id_empleado);
            SELECT @vn_dias_trabajados = dbo.fn_dias_trabajados(@pn_id_periodo, @pn_id_empleado);

            IF @vn_salario_mes IS NOT NULL AND @vn_salario_mes < @vn_salario_integral
            BEGIN
                -- Calcular cesantías y luego intereses (1% mensual)
                SET @vn_cesantias = (@vn_salario_mes * @vn_dias_trabajados) / 360.0;
                SET @vn_intereses = ROUND((@vn_cesantias * 0.12) / 12, 2);

                -- Insertar en detalle_nomina como DEVENGADO
                INSERT INTO detalle_nomina (
                    id_nomina,
                    id_concepto,
                    cantidad,
                    valor_unitario,
                    valor_total,
                    observaciones
                )
                VALUES (
                    @vn_id_nomina,
                    @vn_id_concepto_intereses,
                    NULL,
                    NULL,
                    @vn_intereses,
                    'Intereses sobre cesantías generados automáticamente'
                );
            END
        END

        FETCH NEXT FROM cursor_nominas INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;
    END

    CLOSE cursor_nominas;
    DEALLOCATE cursor_nominas;

    SELECT 'OK' AS estado, 'Intereses sobre cesantías generados exitosamente.' AS mensaje;
END
