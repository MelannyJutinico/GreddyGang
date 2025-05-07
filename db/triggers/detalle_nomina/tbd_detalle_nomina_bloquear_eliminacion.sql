CREATE OR ALTER TRIGGER tbd_detalle_nomina_bloquear_eliminacion
ON detalle_nomina
INSTEAD OF DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar si alguna de las nóminas eliminadas ya fue liquidada o pagada
    IF EXISTS (
        SELECT 1
        FROM deleted d
        INNER JOIN nomina n ON d.id_nomina = n.id_nomina
        WHERE n.estado IN ('LIQUIDADA', 'PAGADA')
    )
    BEGIN
        -- Registrar los intentos en log
        INSERT INTO dbo.log_intento_modificacion_nomina (
            id_nomina,
            id_concepto,
            usuario,
            mensaje
        )
        SELECT 
            d.id_nomina,
            d.id_concepto,
            SUSER_SNAME(),
            'Intento de eliminar detalle de nómina liquidada o pagada'
        FROM deleted d
        INNER JOIN nomina n ON d.id_nomina = n.id_nomina
        WHERE n.estado IN ('LIQUIDADA', 'PAGADA');

        -- Bloquear el borrado
        RAISERROR('No se puede eliminar detalles de una nómina que ya fue liquidada o pagada.', 16, 1);
        RETURN;
    END

    -- Si todo está bien, permitir el DELETE
    DELETE FROM detalle_nomina
    WHERE id_detalle IN (SELECT id_detalle FROM deleted);
END;
