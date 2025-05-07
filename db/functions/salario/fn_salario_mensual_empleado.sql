
-- Función: 
-- Trae el sueldo real del mes para un empleado en un periodo
CREATE OR ALTER FUNCTION fn_salario_mensual_empleado (
    @pn_id_periodo INT,
    @pn_id_empleado INT
)
RETURNS DECIMAL(18,2)
AS
BEGIN
    DECLARE @vn_salario DECIMAL(18,2);
    DECLARE @vn_id_concepto_sueldo INT;

    -- Obtener ID del concepto Sueldo Base
    SELECT @vn_id_concepto_sueldo = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Sueldo Base' AND activo = 1;

    -- Obtener salario desde detalle_nomina
    SELECT @vn_salario = d.valor_total
    FROM detalle_nomina d
    INNER JOIN nomina n ON d.id_nomina = n.id_nomina
    WHERE n.id_periodo = @pn_id_periodo
      AND n.id_empleado = @pn_id_empleado
      AND d.id_concepto = @vn_id_concepto_sueldo;

    RETURN ISNULL(@vn_salario, 0);
END;
