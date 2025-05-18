CREATE OR ALTER VIEW vw_periodos_nomina_cerrados AS
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
    (pn.estado = 'CERRADO')