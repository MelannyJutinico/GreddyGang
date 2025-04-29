CREATE OR ALTER PROCEDURE sp_agregar_novedad
    @pn_id_empleado INT,
    @pn_id_tipo_novedad INT,
    @pd_fecha_inicio DATE,
    @pd_fecha_fin DATE,
    @pn_porcentaje_pago DECIMAL(5,2),
    @vv_observacion VARCHAR(255) = NULL
AS
BEGIN
    SET NOCOUNT ON;

    -- Validar que la fecha inicio no sea mayor a la fecha fin
    IF @pd_fecha_inicio > @pd_fecha_fin
    BEGIN
        RAISERROR('La fecha de inicio no puede ser posterior a la fecha de fin.', 16, 1);
        RETURN;
    END

    -- Validar que el empleado exista
    IF NOT EXISTS (
        SELECT 1
        FROM empleado
        WHERE id_empleado = @pn_id_empleado
          AND activo = 1
    )
    BEGIN
        RAISERROR('El empleado no existe o está inactivo.', 16, 1);
        RETURN;
    END

    -- Validar que el tipo de novedad exista
    IF NOT EXISTS (
        SELECT 1
        FROM tipo_novedad
        WHERE id_tipo_novedad = @pn_id_tipo_novedad
          AND activo = 1
    )
    BEGIN
        RAISERROR('El tipo de novedad no existe o está inactivo.', 16, 1);
        RETURN;
    END

    -- Insertar la novedad
    INSERT INTO novedad (
        id_empleado,
        id_tipo_novedad,
        fecha_inicio,
        fecha_fin,
        porcentaje_pago,
        observacion
    )
    VALUES (
        @pn_id_empleado,
        @pn_id_tipo_novedad,
        @pd_fecha_inicio,
        @pd_fecha_fin,
        @pn_porcentaje_pago,
        @vv_observacion
    );

    -- Devolver mensaje de éxito
    SELECT 'OK' AS estado, 'Novedad registrada exitosamente.' AS mensaje;
END