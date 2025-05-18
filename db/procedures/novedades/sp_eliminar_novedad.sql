CREATE OR ALTER PROCEDURE sp_eliminar_novedad
    @pn_id_novedad INT
    AS
BEGIN
    SET NOCOUNT ON;

    DECLARE
@vn_id_empleado INT,
        @vd_fecha_inicio DATE,
        @vd_fecha_fin DATE;

    -- Validar existencia de la novedad
    IF NOT EXISTS (
        SELECT 1 FROM novedad WHERE id_novedad = @pn_id_novedad
    )
BEGIN
        RAISERROR('La novedad no existe.', 16, 1);
        RETURN;
END

    -- Obtener datos de la novedad
SELECT
    @vn_id_empleado = id_empleado,
    @vd_fecha_inicio = fecha_inicio,
    @vd_fecha_fin = fecha_fin
FROM novedad
WHERE id_novedad = @pn_id_novedad;

-- Validar que el empleado esté activo
IF NOT EXISTS (
        SELECT 1 FROM empleado WHERE id_empleado = @vn_id_empleado AND activo = 1
    )
BEGIN
        RAISERROR('El empleado asociado está inactivo o no existe.', 16, 1);
        RETURN;
END

    -- Verificar si ya hay una nómina generada para el mismo periodo
    IF EXISTS (
        SELECT 1
        FROM nomina n
        INNER JOIN periodo_nomina p ON n.id_periodo = p.id_periodo
        WHERE n.id_empleado = @vn_id_empleado
          AND @vd_fecha_inicio BETWEEN p.fecha_inicio AND p.fecha_fin
          AND n.estado IN ('LIQUIDADA', 'CERRADA')
    )
BEGIN
        RAISERROR('No se puede eliminar la novedad porque ya fue aplicada en una nómina cerrada.', 16, 1);
        RETURN;
END

    -- Eliminar la novedad
DELETE FROM novedad WHERE id_novedad = @pn_id_novedad;

-- Mensaje de éxito
SELECT 'OK' AS estado, 'Novedad eliminada correctamente.' AS mensaje;
END
