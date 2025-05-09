CREATE OR ALTER VIEW vw_conceptos_horas_extra AS
SELECT 
    c.id_concepto,
    c.nombre,
    c.porcentaje,
    c.descripcion,
    c.activo
FROM 
    concepto_nomina c
INNER JOIN 
    tipo_concepto tc ON c.id_tipo_concepto = tc.id_tipo_concepto
WHERE 
    (c.nombre LIKE '%Horas extra%' OR c.nombre LIKE 'Recargo%') AND c.activo = 1;
