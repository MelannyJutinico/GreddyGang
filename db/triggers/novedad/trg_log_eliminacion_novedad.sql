CREATE OR ALTER TRIGGER trg_log_eliminacion_novedad
ON novedad
AFTER DELETE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @usuario VARCHAR(100) = SYSTEM_USER;

INSERT INTO log_novedad (
    id_novedad,
    id_empleado,
    id_tipo_novedad,
    fecha_inicio,
    fecha_fin,
    porcentaje_pago,
    observacion,
    accion,
    motivo,
    usuario,
    fecha_log
)
SELECT
    d.id_novedad,
    d.id_empleado,
    d.id_tipo_novedad,
    d.fecha_inicio,
    d.fecha_fin,
    d.porcentaje_pago,
    d.observacion,
    'ELIMINADA',
    'Novedad eliminada correctamente.',
    @usuario,
    GETDATE()
FROM deleted d;
END
