CREATE OR ALTER PROCEDURE sp_crear_periodo
    @pd_fecha_inicio DATE,
    @pd_fecha_fin DATE,
    @vv_descripcion VARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    -- Validar fechas
    IF @pd_fecha_inicio > @pd_fecha_fin
    BEGIN
        RAISERROR('La fecha de inicio no puede ser mayor a la fecha de fin.', 16, 1);
        RETURN;
    END

    -- Insertar nuevo periodo de nómina
    INSERT INTO periodo_nomina (
        fecha_inicio,
        fecha_fin,
        descripcion,
        estado,
        fecha_creacion
    )
    VALUES (
        @pd_fecha_inicio,
        @pd_fecha_fin,
        @vv_descripcion,
        'ABIERTO',
        GETDATE()
    );

    SELECT 'OK' AS estado, 'Periodo de nómina creado exitosamente.' AS mensaje;
END
