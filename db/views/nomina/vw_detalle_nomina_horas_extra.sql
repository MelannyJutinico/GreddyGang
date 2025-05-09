CREATE OR ALTER VIEW vw_detalle_nomina_horas_extra AS
SELECT 
    dn.id_detalle,
    n.id_nomina,
    e.id_empleado,
    e.nombre AS empleado,
    c.nombre AS concepto_hora_extra,
    dn.cantidad AS horas,
    dn.valor_unitario as valor_hora,
    dn.valor_total ,
    dn.observaciones
FROM detalle_nomina dn
INNER JOIN nomina n ON n.id_nomina = dn.id_nomina
INNER JOIN empleado e ON e.id_empleado = n.id_empleado
INNER JOIN concepto_nomina c ON c.id_concepto = dn.id_concepto
WHERE c.nombre LIKE 'Horas Extra%';
