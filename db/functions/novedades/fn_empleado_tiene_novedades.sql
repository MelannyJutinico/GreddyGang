-- Función: fn_empleado_tiene_novedades
-- Retorna 1 si un empleado tiene al menos una novedad activa en un periodo
CREATE OR ALTER FUNCTION fn_empleado_tiene_novedades (
    @pn_id_periodo INT,
    @pn_id_empleado INT
)
RETURNS BIT
AS
BEGIN
    DECLARE @vn_tiene_novedad BIT = 0;

    IF EXISTS (
        SELECT 1
        FROM novedad n
        INNER JOIN periodo_nomina p ON p.id_periodo = @pn_id_periodo
        WHERE n.id_empleado = @pn_id_empleado
          AND n.fecha_inicio <= p.fecha_fin
          AND n.fecha_fin >= p.fecha_inicio
    )
    BEGIN
        SET @vn_tiene_novedad = 1;
    END

    RETURN @vn_tiene_novedad;
END;
