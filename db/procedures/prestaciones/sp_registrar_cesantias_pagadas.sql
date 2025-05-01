CREATE OR ALTER PROCEDURE sp_registrar_cesantias_pagadas
    @pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @pn_id_empleado INT;
    DECLARE @vn_id_tipo_contrato INT;
    DECLARE @vn_salario_mes DECIMAL(18,2);
    DECLARE @vn_dias_trabajados INT;
    DECLARE @vn_valor_cesantias DECIMAL(18,2);
    DECLARE @vn_salario_integral DECIMAL(18,2);

    -- Obtener el salario definido como integral
    SELECT @vn_salario_integral = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'SALARIO_INTEGRAL' AND activo = 1;

    -- Cursor para empleados con nómina en el periodo
    DECLARE cursor_empleados CURSOR FOR
        SELECT 
            n.id_empleado,
            e.id_tipo_contrato
        FROM nomina n
        INNER JOIN empleado e ON n.id_empleado = e.id_empleado
        WHERE n.id_periodo = @pn_id_periodo
          AND n.estado IN ('GENERADA', 'EN_PROCESO', 'LIQUIDADA')
          AND e.activo = 1;

    OPEN cursor_empleados;
    FETCH NEXT FROM cursor_empleados INTO @pn_id_empleado, @vn_id_tipo_contrato;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- No aplica para prestación de servicios
        IF @vn_id_tipo_contrato <> 4
        BEGIN
            -- Obtener salario y días trabajados reales
            SELECT @vn_salario_mes = dbo.fn_salario_mensual_empleado(@pn_id_periodo, @pn_id_empleado);
            SELECT @vn_dias_trabajados = dbo.fn_dias_trabajados(@pn_id_periodo, @pn_id_empleado);

            -- Solo aplicar si no es salario integral
            IF @vn_salario_mes < @vn_salario_integral
            BEGIN
                SET @vn_valor_cesantias = ROUND((@vn_salario_mes * @vn_dias_trabajados) / 360.0, 2);

                -- Registrar el valor en cesantías pagadas
                INSERT INTO cesantias_pagadas (
                    id_empleado,
                    id_periodo,
                    salario_base,
                    dias_trabajados,
                    valor_cesantias
                )
                VALUES (
                    @pn_id_empleado,
                    @pn_id_periodo,
                    @vn_salario_mes,
                    @vn_dias_trabajados,
                    @vn_valor_cesantias
                );
            END
        END

        FETCH NEXT FROM cursor_empleados INTO @pn_id_empleado, @vn_id_tipo_contrato;
    END

    CLOSE cursor_empleados;
    DEALLOCATE cursor_empleados;

    SELECT 'OK' AS estado, 'Cesantías registradas exitosamente en cesantias_pagadas.' AS mensaje;
END
