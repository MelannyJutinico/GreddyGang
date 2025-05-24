CREATE OR ALTER VIEW vw_primas_generadas AS
SELECT
    n.id_periodo,
    e.id_empleado,
    e.nombre AS nombre_empleado,
    d.valor_total AS valor,
    d.observaciones
FROM detalle_nomina d
         INNER JOIN nomina n ON d.id_nomina = n.id_nomina
         INNER JOIN empleado e ON n.id_empleado = e.id_empleado
         INNER JOIN concepto_nomina c ON d.id_concepto = c.id_concepto
WHERE c.nombre = 'Prima de Servicios';
