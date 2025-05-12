CREATE OR ALTER VIEW vw_tipo_novedad_activo AS

SELECT
	tn.id_tipo_novedad,
	tn.nombre,
	tn.descripcion,
	tn.activo
FROM
	tipo_novedad tn
WHERE
	tn.activo = 1;
