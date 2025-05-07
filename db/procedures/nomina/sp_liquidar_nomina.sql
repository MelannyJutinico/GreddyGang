CREATE OR ALTER PROCEDURE sp_liquidar_nomina
    @pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    -- 1. Actualizar los totales de cada nómina
    UPDATE n
    SET 
        n.total_devengado = ISNULL(dev.total_devengado, 0),
        n.total_deduccion = ISNULL(ded.total_deduccion, 0),
        n.total_a_pagar = ISNULL(dev.total_devengado, 0) - ISNULL(ded.total_deduccion, 0)
    FROM nomina n
    LEFT JOIN (
        SELECT 
            d.id_nomina,
            SUM(d.valor_total) AS total_devengado
        FROM detalle_nomina d
        INNER JOIN concepto_nomina c ON d.id_concepto = c.id_concepto
        INNER JOIN tipo_concepto tc ON c.id_tipo_concepto = tc.id_tipo_concepto
        WHERE tc.nombre = 'DEVENGADO'
        GROUP BY d.id_nomina
    ) dev ON n.id_nomina = dev.id_nomina
    LEFT JOIN (
        SELECT 
            d.id_nomina,
            SUM(d.valor_total) AS total_deduccion
        FROM detalle_nomina d
        INNER JOIN concepto_nomina c ON d.id_concepto = c.id_concepto
        INNER JOIN tipo_concepto tc ON c.id_tipo_concepto = tc.id_tipo_concepto
        WHERE tc.nombre = 'DEDUCCION'
        GROUP BY d.id_nomina
    ) ded ON n.id_nomina = ded.id_nomina
    WHERE n.id_periodo = @pn_id_periodo
      AND n.estado IN ('GENERADA', 'EN_PROCESO');

    -- 2. Cambiar el estado de todas las nóminas a LIQUIDADA
    UPDATE nomina
    SET estado = 'LIQUIDADA'
    WHERE id_periodo = @pn_id_periodo
      AND estado IN ('GENERADA', 'EN_PROCESO');

    -- 3. Marcar el periodo como CERRADO
    UPDATE periodo_nomina
    SET estado = 'CERRADO'
    WHERE id_periodo = @pn_id_periodo;

    SELECT 'OK' AS estado, 'Nómina del periodo liquidada y cerrada exitosamente.' AS mensaje;
END
