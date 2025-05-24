CREATE OR ALTER VIEW vw_periodo_nomina_activo AS
SELECT
	pn.id_periodo,
	pn.fecha_inicio,
	pn.fecha_fin,
	pn.descripcion,
	pn.estado,
	pn.fecha_creacion
FROM
	periodo_nomina pn
WHERE
	(pn.estado = 'ABIERTO') OR (pn.estado = 'EN_PROCESO');