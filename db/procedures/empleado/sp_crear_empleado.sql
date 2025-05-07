CREATE OR ALTER PROCEDURE sp_crear_empleado
    @vv_nombre VARCHAR(100),
    @vv_cargo VARCHAR(100),
    @vv_departamento VARCHAR(100),
    @vn_salario_base DECIMAL(18,2),
    @vb_auxilio BIT,
    @vd_fecha_ingreso DATE,
    @pn_nivel_riesgo INT,
    @pn_tipo_contrato INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Validar que nivel de riesgo exista
    IF NOT EXISTS (SELECT 1 FROM nivel_riesgo_ARL WHERE id_nivel = @pn_nivel_riesgo)
    BEGIN
        RAISERROR('Nivel de riesgo no válido.', 16, 1);
        RETURN;
    END

    -- Validar que tipo de contrato exista
    IF NOT EXISTS (SELECT 1 FROM tipo_contrato WHERE id_tipo_contrato = @pn_tipo_contrato)
    BEGIN
        RAISERROR('Tipo de contrato no válido.', 16, 1);
        RETURN;
    END

    -- Insertar empleado
    INSERT INTO empleado (
        nombre,
        cargo,
        departamento,
        salario_base,
        recibe_auxilio_transporte,
        fecha_ingreso,
        nivel_riesgo,
        id_tipo_contrato,
        activo
    )
    VALUES (
        @vv_nombre,
        @vv_cargo,
        @vv_departamento,
        @vn_salario_base,
        @vb_auxilio,
        @vd_fecha_ingreso,
        @pn_nivel_riesgo,
        @pn_tipo_contrato,
        1
    );

    SELECT 'OK' AS estado, SCOPE_IDENTITY() AS id_empleado_creado;
END
