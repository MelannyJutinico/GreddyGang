CREATE OR ALTER PROCEDURE sp_buscar_empleado_por_id

	@pn_id_empleado INT

AS
BEGIN

	SET NOCOUNT ON;

    -- Validación opcional
    IF NOT EXISTS (SELECT 1 FROM empleado WHERE id_empleado = @pn_id_empleado)
    BEGIN
        RAISERROR('No existe un empleado con el ID especificado.', 16, 1);
        RETURN;
    END

    -- Retornar la información del período
    SELECT 
        e.id_empleado,
		e.nombre,
		e.cargo,
		e.departamento,
		e.salario_base,
		e.recibe_auxilio_transporte,
		e.fecha_ingreso,
		e.activo,
		e.nivel_riesgo,
		e.id_tipo_contrato
    FROM 
        empleado e
    WHERE 
        e.id_empleado = @pn_id_empleado;

END;