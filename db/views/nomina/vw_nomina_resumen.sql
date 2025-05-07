CREATE OR ALTER VIEW vw_nomina_resumen AS
SELECT 
    n.id_nomina,
    e.id_empleado,
    e.nombre AS nombre_empleado,
    p.descripcion AS periodo,
    n.total_devengado,
    n.total_deduccion,
    n.total_a_pagar,
    n.estado,
    n.fecha_liquidacion
FROM nomina n
INNER JOIN empleado e ON e.id_empleado = n.id_empleado
INNER JOIN periodo_nomina p ON p.id_periodo = n.id_periodo;
