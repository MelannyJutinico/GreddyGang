CREATE OR ALTER PROCEDURE sp_aplicar_novedades
    @pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @pn_id_empleado INT;
    DECLARE @vn_id_nomina INT;

    DECLARE @vn_total_dias_afectados DECIMAL(10,2);
    DECLARE @vn_total_factor_pago DECIMAL(10,2);
    DECLARE @vn_factor_normal DECIMAL(10,2);
    DECLARE @vn_factor_total DECIMAL(10,4);

    DECLARE @vn_salario_original DECIMAL(18,2);
    DECLARE @vn_auxilio_original DECIMAL(18,2);

    DECLARE @vn_id_concepto_sueldo INT;
    DECLARE @vn_id_concepto_auxilio INT;

    DECLARE @vn_fecha_inicio DATE;
    DECLARE @vn_fecha_fin DATE;
    DECLARE @vn_porcentaje_pago DECIMAL(5,2);

    -- Obtener ID de conceptos
    SELECT @vn_id_concepto_sueldo = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Sueldo Base' AND activo = 1;

    SELECT @vn_id_concepto_auxilio = id_concepto
    FROM concepto_nomina
    WHERE nombre = 'Auxilio de Transporte' AND activo = 1;

    -- Cursor para recorrer nóminas activas del periodo
    DECLARE empleados_cursor CURSOR FOR
        SELECT 
            no.id_nomina,
            e.id_empleado
        FROM nomina no
        INNER JOIN empleado e ON no.id_empleado = e.id_empleado
        WHERE no.id_periodo = @pn_id_periodo
          AND no.estado IN ('GENERADA', 'EN_PROCESO')
          AND e.activo = 1;

    OPEN empleados_cursor;
    FETCH NEXT FROM empleados_cursor INTO @vn_id_nomina, @pn_id_empleado;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Validar si el empleado tiene novedades en el periodo
        IF dbo.fn_empleado_tiene_novedades(@pn_id_periodo, @pn_id_empleado) = 0
        BEGIN
            FETCH NEXT FROM empleados_cursor INTO @vn_id_nomina, @pn_id_empleado;
            CONTINUE;
        END

        -- Inicializar variables
        SET @vn_total_factor_pago = 0;
        SET @vn_total_dias_afectados = 0;

        PRINT '-----------------------------------------------------------';
        PRINT 'Empleado ID: ' + CAST(@pn_id_empleado AS VARCHAR);

        -- Recorremos las novedades del empleado en el periodo
        DECLARE novedades_cursor CURSOR FOR
            SELECT 
                fecha_inicio,
                fecha_fin,
                porcentaje_pago
            FROM novedad
            WHERE id_empleado = @pn_id_empleado
              AND fecha_inicio <= (SELECT fecha_fin FROM periodo_nomina WHERE id_periodo = @pn_id_periodo)
              AND fecha_fin >= (SELECT fecha_inicio FROM periodo_nomina WHERE id_periodo = @pn_id_periodo);

        OPEN novedades_cursor;
        FETCH NEXT FROM novedades_cursor INTO @vn_fecha_inicio, @vn_fecha_fin, @vn_porcentaje_pago;

        WHILE @@FETCH_STATUS = 0
        BEGIN
            DECLARE @vn_dias_afectados INT;

            -- Calcular días de afectación dentro del periodo
            SELECT @vn_dias_afectados = 
                DATEDIFF(DAY, 
                    CASE WHEN @vn_fecha_inicio < (SELECT fecha_inicio FROM periodo_nomina WHERE id_periodo = @pn_id_periodo) THEN (SELECT fecha_inicio FROM periodo_nomina WHERE id_periodo = @pn_id_periodo) ELSE @vn_fecha_inicio END,
                    CASE WHEN @vn_fecha_fin > (SELECT fecha_fin FROM periodo_nomina WHERE id_periodo = @pn_id_periodo) THEN (SELECT fecha_fin FROM periodo_nomina WHERE id_periodo = @pn_id_periodo) ELSE @vn_fecha_fin END
                ) + 1;

            IF @vn_dias_afectados > 0
            BEGIN
                SET @vn_total_factor_pago = @vn_total_factor_pago + (@vn_dias_afectados * (@vn_porcentaje_pago / 100));
                SET @vn_total_dias_afectados = @vn_total_dias_afectados + @vn_dias_afectados;

                PRINT 'Novedad encontrada: ';
                PRINT ' - Fecha inicio: ' + CAST(@vn_fecha_inicio AS VARCHAR) + ' Fecha fin: ' + CAST(@vn_fecha_fin AS VARCHAR);
                PRINT ' - Días afectados: ' + CAST(@vn_dias_afectados AS VARCHAR);
                PRINT ' - Porcentaje pago: ' + CAST(@vn_porcentaje_pago AS VARCHAR);
                PRINT ' - Factor acumulado: ' + CAST(@vn_total_factor_pago AS VARCHAR);
            END

            FETCH NEXT FROM novedades_cursor INTO @vn_fecha_inicio, @vn_fecha_fin, @vn_porcentaje_pago;
        END

        CLOSE novedades_cursor;
        DEALLOCATE novedades_cursor;

        -- Ahora calcular días normales
        IF @vn_total_dias_afectados < 30
        BEGIN
            SET @vn_factor_normal = (30 - @vn_total_dias_afectados) * 1.0; -- 100% de pago
        END
        ELSE
        BEGIN
            SET @vn_factor_normal = 0; -- Todos los días fueron afectados
        END

        PRINT 'Total días afectados: ' + CAST(@vn_total_dias_afectados AS VARCHAR);
        PRINT 'Días normales: ' + CAST(30 - @vn_total_dias_afectados AS VARCHAR);

        -- Factor total
        SET @vn_factor_total = (@vn_total_factor_pago + @vn_factor_normal) / 30.0;

        PRINT 'Factor de pago final: ' + CAST(@vn_factor_total AS VARCHAR);

        -- Traer salario y auxilio originales
        SELECT @vn_salario_original = valor_total
        FROM detalle_nomina
        WHERE id_nomina = @vn_id_nomina
          AND id_concepto = @vn_id_concepto_sueldo;

        SELECT @vn_auxilio_original = valor_total
        FROM detalle_nomina
        WHERE id_nomina = @vn_id_nomina
          AND id_concepto = @vn_id_concepto_auxilio;

        PRINT 'Salario original: ' + ISNULL(CAST(@vn_salario_original AS VARCHAR), 'NULL');
        PRINT 'Auxilio original: ' + ISNULL(CAST(@vn_auxilio_original AS VARCHAR), 'NULL');

        -- Ajustar salario
        IF @vn_salario_original IS NOT NULL
        BEGIN
            UPDATE detalle_nomina
            SET valor_total = ROUND(@vn_salario_original * @vn_factor_total, 2)
            WHERE id_nomina = @vn_id_nomina
              AND id_concepto = @vn_id_concepto_sueldo;
        END

        -- Ajustar auxilio de transporte
        IF @vn_auxilio_original IS NOT NULL
        BEGIN
            UPDATE detalle_nomina
            SET valor_total = ROUND(@vn_auxilio_original * @vn_factor_total, 2)
            WHERE id_nomina = @vn_id_nomina
              AND id_concepto = @vn_id_concepto_auxilio;
        END

        PRINT '-----------------------------------------------------------';

        FETCH NEXT FROM empleados_cursor INTO @vn_id_nomina, @pn_id_empleado;
    END

    CLOSE empleados_cursor;
    DEALLOCATE empleados_cursor;

    SELECT 'OK' AS estado, 'Novedades aplicadas correctamente considerando varios registros.' AS mensaje;
END
