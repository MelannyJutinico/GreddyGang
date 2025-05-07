package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.entity.DetalleNomina;
import co.edu.unbosque.PayrollAPI.entity.Mensaje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface IDetalleNominaRepository extends CrudRepository<DetalleNomina, Integer> {

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

    @Query(
            value = "EXEC sp_agregar_detalle_nomina :pn_id_nomina, :pn_id_concepto, :pn_valor_total",
            nativeQuery = true
    )
    Mensaje spCrearNomina(
            @Param("pn_id_nomina") Integer pnIdNomina,
            @Param("pn_id_concepto") Integer pnIdConcepto,
            @Param("pn_valor_total") BigDecimal pnValorTotal
    );

    @Query(
            value = "EXEC sp_agregar_horas_extra :pn_id_nomina, :pn_id_empleado, :pn_id_concepto, :vn_cantidad_horas",
            nativeQuery = true
    )
    Mensaje spAgregarHorasExtras(
            @Param("pn_id_nomina") Integer pnIdNomina,
            @Param("pn_id_empleado") Integer pnIdEmpleado,
            @Param("pn_id_concepto") Integer pnIdConcepto,
            @Param("vn_cantidad_horas") BigDecimal vnCantidadHoras
    );

}
