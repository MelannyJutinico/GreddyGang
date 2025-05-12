CREATE OR ALTER VIEW vw_concepto_nomina_devengado_manual_activo AS

SELECT
	cn.id_concepto,
	cn.nombre,
	cn.id_tipo_concepto,
	cn.registro_manual,
	cn.porcentaje,
	cn.descripcion,
	cn.activo
FROM
	concepto_nomina cn
INNER JOIN
	tipo_concepto tc ON cn.id_tipo_concepto = tc.id_tipo_concepto
WHERE
	(tc.nombre = 'DEVENGADO') AND (cn.registro_manual = 1);