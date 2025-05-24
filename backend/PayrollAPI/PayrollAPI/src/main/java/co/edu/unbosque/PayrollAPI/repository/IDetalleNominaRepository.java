package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.DetalleNomina;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IDetalleNominaRepository extends CrudRepository<DetalleNomina, Integer> {

    @Modifying
    @Query(
            value = "EXEC sp_agregar_detalle_nomina :pn_id_nomina, :pn_id_concepto, :pn_valor_total",
            nativeQuery = true
    )
    void spAgregarDetalleNomina(
            @Param("pn_id_nomina") Integer pnIdNomina,
            @Param("pn_id_concepto") Integer pnIdConcepto,
            @Param("pn_valor_total") BigDecimal pnValorTotal
    );


    @Query(
            value = "EXEC sp_generar_deducciones_automaticas :pn_id_periodo",
            nativeQuery = true
    )
    Mensaje spGenerarDecuccionesAutomaticas(
            @Param("pn_id_periodo ") Integer pnIdPeriodo
    );

    @Query(
            value = "EXEC sp_generar_intereses_cesantias :pn_id_periodo",
            nativeQuery = true
    )
    Mensaje spGenerarInteresesCesantias(
            @Param("pn_id_periodo ") Integer pnIdPeriodo
    );


    @Query(
            value = "EXEC sp_generar_prima_servicios :pn_id_periodo",
            nativeQuery = true
    )
    Mensaje spGenerarPrimaServicios(
            @Param("pn_id_periodo ") Integer pnIdPeriodo
    );

    @Query(value = "EXEC sp_listar_detalle_nomina_empleado :idEmpleado, :idPeriodo", nativeQuery = true)
    List<Object[]> spListarDetalleNominaEmpleado(
            @Param("idEmpleado") Integer idEmpleado,
            @Param("idPeriodo") Integer idPeriodo
    );

    @Query(value = """
        SELECT 
         *
        FROM vw_detalle_nomina_horas_extra
        WHERE id_empleado = :idEmpleado AND id_periodo = :idPeriodo
    """, nativeQuery = true)
    List<Object[]> listarHorasExtrasPorEmpleadoYPeriodo(@Param("idEmpleado") Integer idEmpleado,
                                                        @Param("idPeriodo") Integer idPeriodo);



}
