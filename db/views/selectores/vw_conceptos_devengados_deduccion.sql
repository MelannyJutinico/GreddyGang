CREATE OR ALTER VIEW vw_conceptos_devengados_deduccion AS
SELECT 
    c.id_concepto,
    c.nombre,
    tc.nombre AS tipo_concepto,
    c.descripcion
FROM 
    concepto_nomina c
INNER JOIN 
    tipo_concepto tc ON c.id_tipo_concepto = tc.id_tipo_concepto
WHERE 
    c.registro_manual = 1
    AND c.activo = 1
    AND LOWER(c.nombre) NOT LIKE '%horas extra%'
    AND LOWER(c.nombre) NOT LIKE '%recargo%';