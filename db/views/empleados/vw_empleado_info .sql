CREATE OR ALTER VIEW vw_empleado_info AS
SELECT 
    e.id_empleado,
    e.nombre,
    e.cargo,
    e.departamento,
    e.salario_base,
    e.recibe_auxilio_transporte,
    e.fecha_ingreso,
    tc.nombre AS tipo_contrato,
    arl.nombre AS nivel_riesgo_nombre,
    arl.porcentaje AS porcentaje_riesgo,
    e.activo
FROM empleado e
INNER JOIN tipo_contrato tc ON e.id_tipo_contrato = tc.id_tipo_contrato
INNER JOIN nivel_riesgo_ARL arl ON e.nivel_riesgo = arl.id_nivel;
