CREATE OR ALTER TRIGGER trg_validar_eliminacion_novedad
    ON novedad
    INSTEAD OF DELETE
    AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @usuario VARCHAR(100) = SYSTEM_USER;

    -- Procesar todas las filas eliminadas (no solo TOP 1)
    DECLARE @id_novedad INT, @id_empleado INT, @id_tipo_novedad INT;
    DECLARE @fecha_inicio DATE, @fecha_fin DATE, @porcentaje_pago DECIMAL(5,2), @observacion VARCHAR(255);

    DECLARE cur CURSOR FOR
        SELECT d.id_novedad, n.id_empleado, n.id_tipo_novedad, n.fecha_inicio, n.fecha_fin, n.porcentaje_pago, n.observacion
        FROM deleted d
                 JOIN novedad n ON d.id_novedad = n.id_novedad;

    OPEN cur;

    FETCH NEXT FROM cur INTO @id_novedad, @id_empleado, @id_tipo_novedad, @fecha_inicio, @fecha_fin, @porcentaje_pago, @observacion;

    WHILE @@FETCH_STATUS = 0
        BEGIN
            IF EXISTS (
                SELECT 1
                FROM nomina no
                         JOIN periodo_nomina p ON no.id_periodo = p.id_periodo
                WHERE no.id_empleado = @id_empleado
                  AND @fecha_inicio BETWEEN p.fecha_inicio AND p.fecha_fin
                  AND no.estado IN ('LIQUIDADA', 'CERRADA')
            )
                BEGIN
                    INSERT INTO log_novedad (
                        id_novedad, id_empleado, id_tipo_novedad, fecha_inicio, fecha_fin,
                        porcentaje_pago, observacion, accion, motivo, usuario, fecha_log
                    )
                    VALUES (
                               @id_novedad, @id_empleado, @id_tipo_novedad, @fecha_inicio, @fecha_fin,
                               @porcentaje_pago, @observacion,
                               'INTENTO_ELIMINAR_APLICADA',
                               'Se intentó eliminar una novedad ya aplicada en nómina cerrada.',
                               @usuario, GETDATE()
                           );

                    RAISERROR('No se puede eliminar: la novedad ya fue aplicada en una nómina cerrada.', 16, 1);
                    CLOSE cur;
                    DEALLOCATE cur;
                    RETURN;
                END

            -- Eliminar la novedad permitida
            DELETE FROM novedad WHERE id_novedad = @id_novedad;

            FETCH NEXT FROM cur INTO @id_novedad, @id_empleado, @id_tipo_novedad, @fecha_inicio, @fecha_fin, @porcentaje_pago, @observacion;
        END

    CLOSE cur;
    DEALLOCATE cur;
END
