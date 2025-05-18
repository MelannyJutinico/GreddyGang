CREATE OR ALTER PROCEDURE sp_agregar_horas_extra
    @pn_id_nomina INT,
    @pn_id_empleado INT,
    @pn_id_concepto INT,
    @vn_cantidad_horas DECIMAL(10,2)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @vn_salario_base DECIMAL(18,2);
    DECLARE @vn_valor_hora DECIMAL(18,2);
    DECLARE @vn_porcentaje DECIMAL(5,2);
    DECLARE @vn_valor_unitario DECIMAL(18,2);
    DECLARE @vn_valor_total DECIMAL(18,2);

    -- Validar existencia de nómina
    IF NOT EXISTS (SELECT 1 FROM nomina WHERE id_nomina = @pn_id_nomina)
    BEGIN
        RAISERROR('La nómina especificada no existe.', 16, 1);
        RETURN;
    END

    -- Validar existencia de empleado
    IF NOT EXISTS (SELECT 1 FROM empleado WHERE id_empleado = @pn_id_empleado)
    BEGIN
        RAISERROR('El empleado especificado no existe.', 16, 1);
        RETURN;
    END

    -- Validar existencia de concepto
    IF NOT EXISTS (SELECT 1 FROM concepto_nomina WHERE id_concepto = @pn_id_concepto)
    BEGIN
        RAISERROR('El concepto de nómina especificado no existe.', 16, 1);
        RETURN;
    END

         -- Validar que la nómina NO esté liquidada
    IF EXISTS (
        SELECT 1 FROM nomina 
        WHERE id_nomina = @pn_id_nomina AND estado = 'LIQUIDADA'
    )
    BEGIN
        RAISERROR('No se puede modificar una nómina ya liquidada.', 16, 1);
        RETURN;
    END

    -- Obtener salario base
    SELECT @vn_salario_base = salario_base
    FROM empleado
    WHERE id_empleado = @pn_id_empleado;

    -- Obtener porcentaje del concepto
    SELECT @vn_porcentaje = porcentaje
    FROM concepto_nomina
    WHERE id_concepto = @pn_id_concepto;

    IF @vn_porcentaje IS NULL
    BEGIN
        RAISERROR('El concepto seleccionado no tiene porcentaje configurado.', 16, 1);
        RETURN;
    END

    -- Calcular valor hora normal
    SET @vn_valor_hora = @vn_salario_base / 30 / 8;

    -- Calcular valor unitario de la hora extra
    SET @vn_valor_unitario = @vn_valor_hora * (@vn_porcentaje / 100);

    -- Calcular valor total
    SET @vn_valor_total = @vn_valor_unitario * @vn_cantidad_horas;

    -- Insertar en detalle_nomina
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
        @vn_cantidad_horas,
        @vn_valor_unitario,
        @vn_valor_total
    );

    
    RETURN;
END
