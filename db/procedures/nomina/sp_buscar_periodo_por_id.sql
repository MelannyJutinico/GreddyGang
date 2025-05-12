CREATE OR ALTER PROCEDURE sp_buscar_periodo_por_id
    @pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Validaci�n opcional
    IF NOT EXISTS (SELECT 1 FROM periodo_nomina WHERE id_periodo = @pn_id_periodo)
    BEGIN
        RAISERROR('No existe un per�odo con el ID especificado.', 16, 1);
        RETURN;
    END

    -- Retornar la informaci�n del per�odo
    SELECT 
        id_periodo,
        fecha_inicio,
        fecha_fin,
        descripcion,
        estado,
        fecha_creacion
    FROM 
        periodo_nomina
    WHERE 
        id_periodo = @pn_id_periodo;
END;
