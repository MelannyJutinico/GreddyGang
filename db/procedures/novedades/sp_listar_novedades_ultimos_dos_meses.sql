CREATE PROCEDURE sp_listar_novedades_ultimos_dos_meses
		@pn_id_empleado INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        tn.nombre AS tipo_novedad,
        n.fecha_inicio,
        n.fecha_fin,
        n.observacion
    FROM 
		novedad n
    INNER JOIN 
		tipo_novedad tn ON n.id_tipo_novedad = tn.id_tipo_novedad
    WHERE 
		(n.fecha_inicio >= DATEADD(MONTH, -2, GETDATE())) AND (n.id_empleado = @pn_id_empleado)
    ORDER BY 
		n.fecha_inicio DESC;
END;






