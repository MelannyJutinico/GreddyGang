CREATE OR ALTER PROCEDURE sp_cambiar_estado_periodo
    @pn_id_periodo INT,
    @vv_nuevo_estado VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;

    -- Validar que el periodo exista
    IF NOT EXISTS (
        SELECT 1
        FROM periodo_nomina
        WHERE id_periodo = @pn_id_periodo
    )
    BEGIN
        RAISERROR('El periodo especificado no existe.', 16, 1);
        RETURN;
    END

    -- Validar que el estado nuevo sea válido
    IF @vv_nuevo_estado NOT IN ('ABIERTO', 'EN_PROCESO', 'CERRADO', 'ANULADO')
    BEGIN
        RAISERROR('Estado no permitido. Estados válidos: ABIERTO, EN_PROCESO, CERRADO, ANULADO.', 16, 1);
        RETURN;
    END

    -- Actualizar el estado del periodo
    UPDATE periodo_nomina
    SET estado = @vv_nuevo_estado
    WHERE id_periodo = @pn_id_periodo;


    SELECT 'OK' AS estado, 'Estado del periodo actualizado exitosamente.'  AS mensaje;
END
