CREATE OR ALTER TRIGGER trg_log_estado_nomina
ON nomina
AFTER UPDATE
                          AS
BEGIN
    SET NOCOUNT ON;

INSERT INTO log_estado_nomina (
    id_nomina,
    estado_anterior,
    estado_nuevo,
    usuario,
    fecha_log
)
SELECT
    i.id_nomina,
    d.estado,
    i.estado,
    SYSTEM_USER,
    GETDATE()
FROM inserted i
         JOIN deleted d ON i.id_nomina = d.id_nomina
WHERE i.estado <> d.estado;
END;
