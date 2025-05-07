CREATE OR ALTER TRIGGER tbi_detalle_nomina_validar_estado
ON detalle_nomina
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Solo las filas que apuntan a nóminas liquidadas
    IF EXISTS (
        SELECT 1
        FROM inserted i
        INNER JOIN nomina n ON i.id_nomina = n.id_nomina
        WHERE n.estado = 'LIQUIDADA'
    )
    BEGIN
        -- Registrar en log
        INSERT INTO dbo.log_intento_modificacion_nomina (
            id_nomina,
            id_concepto,
            usuario,
            mensaje
        )
        SELECT 
            i.id_nomina,
            i.id_concepto,
            SUSER_SNAME(),
            'Intento de insertar en nómina liquidada'
        FROM inserted i
        INNER JOIN nomina n ON i.id_nomina = n.id_nomina
        WHERE n.estado = 'LIQUIDADA';

        -- Bloquear la operación
        RAISERROR('No se puede insertar en una nómina ya liquidada.', 16, 1);
        RETURN;
    END

    -- Insertar normalmente si no está liquidada
    INSERT INTO detalle_nomina (
        id_nomina,
        id_concepto,
        cantidad,
        valor_unitario,
        valor_total,
        observaciones
    )
    SELECT
        id_nomina,
        id_concepto,
        cantidad,
        valor_unitario,
        valor_total,
        observaciones
    FROM inserted;
END;
