CREATE OR ALTER PROCEDURE sp_generar_nomina_masiva
    @pn_id_periodo INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @pn_id_empleado INT;
    DECLARE @vn_salario_base DECIMAL(18,2);
    DECLARE @vn_id_tipo_contrato INT;
    DECLARE @vn_valor_auxilio DECIMAL(18,2);
    DECLARE @vn_smmlv DECIMAL(18,2);
    DECLARE @vn_tope_auxilio DECIMAL(18,2);
    DECLARE @vn_salario_auxilio_max DECIMAL(18,2);
    DECLARE @vn_id_nomina INT;

    -- Cargar parámetros de la tabla parametro_nomina
    SELECT @vn_smmlv = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'SALARIO_MINIMO' AND activo = 1;

    SELECT @vn_valor_auxilio = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'AUXILIO_TRANSPORTE' AND activo = 1;

    SELECT @vn_tope_auxilio = valor_numerico
    FROM parametro_nomina
    WHERE nombre = 'SMMLV_TOPE_AUXILIO' AND activo = 1;

    -- Calcular máximo salario para aplicar auxilio
    SET @vn_salario_auxilio_max = @vn_smmlv * @vn_tope_auxilio;

    -- Validar existencia del periodo y que esté ABIERTO
    IF NOT EXISTS (
        SELECT 1 FROM periodo_nomina
        WHERE id_periodo = @pn_id_periodo
          AND estado = 'ABIERTO'
    )
    BEGIN
        RAISERROR('El periodo no existe o no está en estado ABIERTO.', 16, 1);
        RETURN;
    END

    -- Cursor para recorrer empleados activos
    DECLARE empleados_cursor CURSOR FOR
        SELECT id_empleado
        FROM empleado
        WHERE activo = 1;

    OPEN empleados_cursor;
    FETCH NEXT FROM empleados_cursor INTO @pn_id_empleado;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Verificar si ya existe nómina para ese empleado en ese periodo
        IF NOT EXISTS (
            SELECT 1
            FROM nomina
            WHERE id_empleado = @pn_id_empleado
              AND id_periodo = @pn_id_periodo
        )
        BEGIN
            -- Insertar nueva nómina
            INSERT INTO nomina (
                id_empleado,
                id_periodo,
                fecha_liquidacion,
                estado
            )
            VALUES (
                @pn_id_empleado,
                @pn_id_periodo,
                GETDATE(),
                'GENERADA'
            );

            -- Obtener el ID de la nómina recién creada
            SET @vn_id_nomina = SCOPE_IDENTITY();

            -- Obtener salario base
            SELECT @vn_salario_base = salario_base,
                     @vn_id_tipo_contrato = id_tipo_contrato
            FROM empleado
            WHERE id_empleado = @pn_id_empleado;

            -- Insertar sueldo base como devengado
            INSERT INTO detalle_nomina (
                id_nomina,
                id_concepto,
                cantidad,
                valor_unitario,
                valor_total
            )
            VALUES (
                @vn_id_nomina,
                1,  -- id_concepto 1 = Sueldo Base
                NULL,
                NULL,
                @vn_salario_base
            );

            -- Evaluar si aplica Auxilio de Transporte
            IF @vn_salario_base <= @vn_salario_auxilio_max AND @vn_id_tipo_contrato <> 4 -- 4 = Prestación de servicios
            BEGIN
                INSERT INTO detalle_nomina (
                    id_nomina,
                    id_concepto,
                    cantidad,
                    valor_unitario,
                    valor_total
                )
                VALUES (
                    @vn_id_nomina,
                    2,  -- id_concepto 2 = Auxilio de Transporte
                    NULL,
                    NULL,
                    @vn_valor_auxilio
                );
            END
        END

        FETCH NEXT FROM empleados_cursor INTO @pn_id_empleado;
    END

    CLOSE empleados_cursor;
    DEALLOCATE empleados_cursor;


    SELECT 'OK' AS estado, 'Nóminas masivas generadas exitosamente.' AS mensaje;
    
END

