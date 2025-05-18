CREATE OR ALTER PROCEDURE sp_liquidar_periodo
    @pn_id_periodo INT
    AS
BEGIN
    SET NOCOUNT ON;

BEGIN TRY
BEGIN TRAN;

        -- Paso 1: Aplicar novedades
EXEC sp_aplicar_novedades @pn_id_periodo;

        -- Paso 2: Generar deducciones automáticas
EXEC sp_generar_deducciones_automaticas @pn_id_periodo;

        -- Paso 3: Generar aportes patronales
EXEC sp_generar_aportes_patronales @pn_id_periodo;

        -- Paso 4: Generar provisiones
EXEC sp_generar_provisiones @pn_id_periodo;

        -- Paso 5: Liquidar nómina y cerrar período
EXEC sp_liquidar_nomina @pn_id_periodo;

COMMIT;

SELECT 'OK' AS estado, 'Liquidación completada exitosamente para el período.' AS mensaje;
END TRY
BEGIN CATCH
ROLLBACK;

        DECLARE @errorMsg NVARCHAR(4000) = ERROR_MESSAGE();
        RAISERROR('Error al liquidar el período: %s', 16, 1, @errorMsg);
END CATCH
END;
