CREATE OR ALTER PROCEDURE sp_agregar_detalle_nomina
    @pn_id_nomina INT,
    @pn_id_concepto INT,
    @pn_valor_total DECIMAL(18,2)
AS
BEGIN
    SET NOCOUNT ON;

    -- Validar que la nómina exista
    IF NOT EXISTS (
        SELECT 1
        FROM nomina
        WHERE id_nomina = @pn_id_nomina
    )
    BEGIN
        RAISERROR('La nómina especificada no existe.', 16, 1);
        RETURN;
    END

    -- Validar que el concepto exista
    IF NOT EXISTS (
        SELECT 1
        FROM concepto_nomina
        WHERE id_concepto = @pn_id_concepto
    )
    BEGIN
        RAISERROR('El concepto de nómina especificado no existe.', 16, 1);
        RETURN;
    END

    -- Insertar el detalle de la nómina
    INSERT INTO detalle_nomina (
        id_nomina,
        id_concepto,
        cantidad,
        valor_unitario,
        valor_total
    )
    VALUES (
        @pn_id_nomina,
        @pn_id_concepto,
        @pn_valor_total
    );

    SELECT 'OK' AS estado, 'Nóminas masivas generadas exitosamente.' AS mensaje;
END

