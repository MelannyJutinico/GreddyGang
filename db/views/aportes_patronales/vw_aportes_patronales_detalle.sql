CREATE OR ALTER VIEW vw_aportes_patronales_detalle AS
SELECT 
    ap.id_aporte,
    n.id_nomina,
    e.nombre AS empleado,
    tap.nombre AS tipo_aporte,
    ap.cantidad
FROM aporte_patronal ap
INNER JOIN nomina n ON n.id_nomina = ap.id_nomina
INNER JOIN empleado e ON e.id_empleado = n.id_empleado
INNER JOIN tipo_aporte_patronal tap ON tap.id_tipo_aporte = ap.id_tipo_aporte;
