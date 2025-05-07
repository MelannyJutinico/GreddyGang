CREATE OR ALTER PROCEDURE sp_generar_prima_servicios
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
    DECLARE @vn_dias_trabajados INT;

    DECLARE @vn_id_concepto_sueldo INT;
    DECLARE @vn_id_concepto_prima INT;

    -- Obtener salario integral
    SELECT @vn_salario_integral = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'SALARIO_INTEGRAL' AND activo = 1;

    -- ID del concepto sueldo base
    SELECT @vn_id_concepto_sueldo = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Sueldo Base' AND activo = 1;

    -- ID del concepto de prima
    SELECT @vn_id_concepto_prima = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Prima de Servicios' AND activo = 1;

    -- Recorremos todas las nóminas activas del periodo
    DECLARE cursor_nominas CURSOR FOR
        SELECT 
            n.id_nomina,
            e.id_empleado,
            e.id_tipo_contrato
        FROM nomina n
        INNER JOIN empleado e ON n.id_empleado = e.id_empleado
        WHERE n.id_periodo = @pn_id_periodo
          AND n.estado IN ('GENERADA', 'EN_PROCESO')
          AND e.activo = 1;

    OPEN cursor_nominas;
    FETCH NEXT FROM cursor_nominas INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Solo si NO es prestación de servicios
        IF @vn_id_tipo_contrato <> 4
        BEGIN
            -- Obtener salario real del mes
            SELECT @vn_salario_mes = dbo.fn_salario_mensual_empleado(@pn_id_periodo, @pn_id_empleado);

            -- Obtener días trabajados reales
            SELECT @vn_dias_trabajados = dbo.fn_dias_trabajados(@pn_id_periodo, @pn_id_empleado);

            -- Solo generar si tiene salario válido y no es salario integral
            IF @vn_salario_mes IS NOT NULL AND @vn_salario_mes < @vn_salario_integral
            BEGIN
                SET @vn_base_cotizacion = @vn_salario_mes;

                -- Calcular prima
                SET @vn_prima = ROUND((@vn_base_cotizacion * @vn_dias_trabajados) / 360.0, 2);

                -- Insertar como detalle de nómina
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
                    @vn_id_concepto_prima,
                    NULL,
                    NULL,
                    @vn_prima,
                    'Prima semestral generada automáticamente'
                );
            END
        END

        FETCH NEXT FROM cursor_nominas INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;
    END

    CLOSE cursor_nominas;
    DEALLOCATE cursor_nominas;

    SELECT 'OK' AS estado, 'Primas generadas exitosamente.' AS mensaje;
END
