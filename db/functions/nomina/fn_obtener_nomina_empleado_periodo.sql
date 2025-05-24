CREATE OR ALTER FUNCTION fn_obtener_nomina_empleado_periodo (
    @pn_id_empleado INT,
    @pn_id_periodo INT
    )
    RETURNS TABLE
    AS
    RETURN (
    SELECT n.id_nomina
    FROM nomina n
    WHERE n.id_empleado = @pn_id_empleado
    AND n.id_periodo = @pn_id_periodo
    );
