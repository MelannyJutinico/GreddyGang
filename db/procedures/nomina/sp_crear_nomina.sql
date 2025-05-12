CREATE OR ALTER PROCEDURE sp_crear_nomina
    @pn_id_empleado INT,
    @pn_id_periodo INT,
    @pd_fecha_liquidacion DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    -- Validar si ya existe una nómina para el empleado y el periodo
    IF EXISTS (
        SELECT 1
        FROM nomina
        WHERE id_empleado = @pn_id_empleado
          AND id_periodo = @pn_id_periodo
    )
    BEGIN
        RAISERROR('Ya existe una nómina para este empleado y este periodo.', 16, 1);
        RETURN;
    END

    -- Insertar nueva nómina
    INSERT INTO nomina (
        id_empleado,
        id_periodo,
        fecha_liquidacion,
        estado
    )
    VALUES (
        @pn_id_empleado,
        @pn_id_periodo,
        ISNULL(@pd_fecha_liquidacion, GETDATE()),
        'GENERADA'
    );


    SELECT 'OK' AS estado, 'Nómina creada exitosamente.'  AS mensaje;
END
