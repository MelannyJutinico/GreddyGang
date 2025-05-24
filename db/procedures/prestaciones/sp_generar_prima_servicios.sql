CREATE OR ALTER PROCEDURE sp_generar_prima_servicios
@pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @pn_id_empleado INT;
    DECLARE @vn_id_nomina INT;
    DECLARE @vn_id_tipo_contrato INT;
    DECLARE @vn_prima DECIMAL(18,2);

    DECLARE @vn_id_concepto_prima INT;
    DECLARE @vn_id_concepto_provision_prima INT;

    -- Validar mes correcto
    IF MONTH((SELECT fecha_fin FROM periodo_nomina WHERE id_periodo = @pn_id_periodo)) NOT IN (6, 12)
        BEGIN
            RAISERROR('La prima solo se puede generar en junio o diciembre.', 16, 1);
            RETURN;
        END

    -- Obtener ID del concepto de prima (detalle_nomina)
    SELECT @vn_id_concepto_prima = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Prima de Servicios' AND activo = 1;

    -- Obtener ID del concepto de prima (provision_prestaciones)
    SELECT @vn_id_concepto_provision_prima = id_concepto_provision
    FROM concepto_provision
    WHERE nombre = 'Prima de Servicios' AND activo = 1;

    -- Validar si ya se generó
    IF EXISTS (
        SELECT 1
        FROM detalle_nomina dn
                 INNER JOIN nomina n ON n.id_nomina = dn.id_nomina
        WHERE n.id_periodo = @pn_id_periodo
          AND dn.id_concepto = @vn_id_concepto_prima
    )
        BEGIN
            RAISERROR('Ya se ha generado la Prima de Servicios para este período.', 16, 1);
            RETURN;
        END

    -- Cursor por empleados y nóminas del periodo
    DECLARE cursor_nominas CURSOR FOR
        SELECT
            n.id_nomina,
            e.id_empleado,
            e.id_tipo_contrato
        FROM nomina n
                 INNER JOIN empleado e ON n.id_empleado = e.id_empleado
        WHERE n.id_periodo = @pn_id_periodo
          AND n.estado IN ('GENERADA', 'EN_PROCESO')
          AND e.activo = 1;

    OPEN cursor_nominas;
    FETCH NEXT FROM cursor_nominas INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;

    WHILE @@FETCH_STATUS = 0
        BEGIN
            -- Solo si no es prestación de servicios
            IF @vn_id_tipo_contrato <> 4
                BEGIN
                    -- Sumar provisiones de la prima en el semestre
                    SELECT @vn_prima = SUM(p.cantidad)
                    FROM provision_prestaciones p
                             INNER JOIN periodo_nomina per ON p.id_periodo = per.id_periodo
                    WHERE p.id_empleado = @pn_id_empleado
                      AND p.id_concepto_provision = @vn_id_concepto_provision_prima
                      AND YEAR(per.fecha_fin) = (SELECT YEAR(fecha_fin) FROM periodo_nomina WHERE id_periodo = @pn_id_periodo)
                      AND (
                        (MONTH(per.fecha_fin) BETWEEN 1 AND 6 AND MONTH((SELECT fecha_fin FROM periodo_nomina WHERE id_periodo = @pn_id_periodo)) = 6)
                            OR (MONTH(per.fecha_fin) BETWEEN 7 AND 12 AND MONTH((SELECT fecha_fin FROM periodo_nomina WHERE id_periodo = @pn_id_periodo)) = 12)
                        );

                    IF @vn_prima IS NOT NULL AND @vn_prima > 0
                        BEGIN
                            INSERT INTO detalle_nomina (
                                id_nomina,
                                id_concepto,
                                cantidad,
                                valor_unitario,
                                valor_total,
                                observaciones
                            )
                            VALUES (
                                       @vn_id_nomina,
                                       @vn_id_concepto_prima,
                                       NULL,
                                       NULL,
                                       ROUND(@vn_prima, 2),
                                       'Prima semestral generada con base en provisiones'
                                   );
                        END
                END

            FETCH NEXT FROM cursor_nominas INTO @vn_id_nomina, @pn_id_empleado, @vn_id_tipo_contrato;
        END

    CLOSE cursor_nominas;
    DEALLOCATE cursor_nominas;

    SELECT 'OK' AS estado, 'Primas generadas con base en provisiones del semestre.' AS mensaje;
END;
