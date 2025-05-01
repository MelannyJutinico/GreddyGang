CREATE OR ALTER VIEW vw_detalle_nomina_normal  AS
SELECT 
    dn.id_detalle,
    n.id_nomina,
    e.nombre AS empleado,
    c.nombre AS concepto,
    tc.nombre AS tipo_concepto,
    dn.valor_total,
    dn.observaciones
FROM detalle_nomina dn
INNER JOIN nomina n ON n.id_nomina = dn.id_nomina
INNER JOIN empleado e ON e.id_empleado = n.id_empleado
INNER JOIN concepto_nomina c ON c.id_concepto = dn.id_concepto
INNER JOIN tipo_concepto tc ON tc.id_tipo_concepto = c.id_tipo_concepto
WHERE c.nombre NOT LIKE 'Horas Extra%';