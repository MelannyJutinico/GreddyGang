-- Función: 
-- Calcula cuántos días trabajó un empleado en un periodo considerando novedades
CREATE OR ALTER FUNCTION fn_dias_trabajados (
    @pn_id_periodo INT,
    @pn_id_empleado INT
)
RETURNS INT
AS
BEGIN
    DECLARE @vn_fecha_inicio DATE, @vn_fecha_fin DATE;
    DECLARE @vn_dias_total INT = 0;
    DECLARE @vn_dias_afectados INT = 0;
    DECLARE @vn_dias_trabajados INT;

    -- Obtener rango del periodo
    SELECT @vn_fecha_inicio = fecha_inicio, @vn_fecha_fin = fecha_fin
    FROM periodo_nomina
    WHERE id_periodo = @pn_id_periodo;

    -- Días totales del periodo
    SET @vn_dias_total = DATEDIFF(DAY, @vn_fecha_inicio, @vn_fecha_fin) + 1;

    -- Sumar días afectados por novedades
    SELECT @vn_dias_afectados = ISNULL(SUM(
        DATEDIFF(DAY, 
            CASE WHEN fecha_inicio < @vn_fecha_inicio THEN @vn_fecha_inicio ELSE fecha_inicio END,
            CASE WHEN fecha_fin > @vn_fecha_fin THEN @vn_fecha_fin ELSE fecha_fin END
        ) + 1
    ), 0)
    FROM novedad
    WHERE id_empleado = @pn_id_empleado
      AND fecha_inicio <= @vn_fecha_fin
      AND fecha_fin >= @vn_fecha_inicio
      AND porcentaje_pago = 0;

    -- Calcular días trabajados reales
    SET @vn_dias_trabajados = @vn_dias_total - @vn_dias_afectados;

    RETURN @vn_dias_trabajados;
END;
