CREATE OR ALTER VIEW vw_cesantias_pagadas AS
SELECT
    cp.id_periodo,
    e.id_empleado,
    e.nombre AS nombre_empleado,
    cp.salario_base,
    cp.dias_trabajados,
    cp.valor_cesantias
FROM cesantias_pagadas cp
         INNER JOIN empleado e ON cp.id_empleado = e.id_empleado;
