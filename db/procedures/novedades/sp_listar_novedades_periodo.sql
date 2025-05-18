CREATE OR ALTER PROCEDURE sp_listar_novedades_periodo
    @pn_id_empleado INT,
    @pn_id_periodo INT
    AS
BEGIN
SELECT n.*,
       tn.nombre AS tipo_novedad
FROM novedad n
         INNER JOIN
     tipo_novedad tn ON n.id_tipo_novedad = tn.id_tipo_novedad
         JOIN periodo_nomina p ON MONTH(n.fecha_inicio) = MONTH(p.fecha_inicio)
    AND YEAR(n.fecha_inicio) = YEAR(p.fecha_inicio)
WHERE n.id_empleado = @pn_id_empleado
  AND p.id_periodo = @pn_id_periodo
END






