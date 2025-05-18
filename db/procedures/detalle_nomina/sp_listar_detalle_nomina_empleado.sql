CREATE OR ALTER PROCEDURE sp_listar_detalle_nomina_empleado
    @pn_id_empleado INT,
    @pn_id_periodo INT
    AS
BEGIN
    SET NOCOUNT ON;

SELECT
    dn.id_detalle,
    tc.nombre AS tipo,
    c.nombre AS nombre,
    dn.valor_total AS valor
FROM detalle_nomina dn
         INNER JOIN concepto_nomina c ON dn.id_concepto = c.id_concepto
         INNER JOIN tipo_concepto tc ON c.id_tipo_concepto = tc.id_tipo_concepto
         INNER JOIN nomina n ON dn.id_nomina = n.id_nomina
WHERE n.id_empleado = @pn_id_empleado
  AND n.id_periodo = @pn_id_periodo;
END;
