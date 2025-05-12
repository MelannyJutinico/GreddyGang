CREATE OR ALTER VIEW vw_empleados_activos AS
SELECT
	*
FROM
	empleado e
WHERE
	e.activo = 1;