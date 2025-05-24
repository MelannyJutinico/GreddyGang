CREATE OR ALTER FUNCTION fn_existe_nomina_por_periodo
    (
    @pn_id_periodo INT
    )
    RETURNS BIT
    AS
BEGIN
    DECLARE @existe BIT;

    IF EXISTS (
        SELECT 1
        FROM nomina
        WHERE id_periodo = @pn_id_periodo
    )
        SET @existe = 1;
ELSE
        SET @existe = 0;

RETURN @existe;
END
