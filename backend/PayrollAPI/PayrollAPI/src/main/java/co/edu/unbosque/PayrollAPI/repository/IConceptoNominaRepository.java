package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.entity.ConceptoNomina;
import co.edu.unbosque.PayrollAPI.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.projection.ConceptoHoraExtraProjection;
import co.edu.unbosque.PayrollAPI.projection.ConceptoTipoConceptoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IConceptoNominaRepository extends CrudRepository<ConceptoNomina, Integer> {


    @Query(
            value = "SELECT * FROM vw_conceptos_devengados_deduccion",
            nativeQuery = true
    )
    List<ConceptoTipoConceptoProjection> vwConceptosDevengadosDeduccion();

    @Query(
            value = "SELECT * FROM vw_conceptos_horas_extra",
            nativeQuery = true
    )
    List<ConceptoHoraExtraProjection> vwConceptosHorasExtra();

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

    @Query(
            value = "EXEC sp_agregar_detalle_nomina :pn_id_nomina, :pn_id_concepto, :pn_valor_total",
            nativeQuery = true
    )
    Mensaje spCrearNomina(
            @Param("pn_id_nomina") Integer pnIdNomina,
            @Param("pn_id_concepto") Integer pnIdConcepto,
            @Param("pn_valor_total") BigDecimal pnValorTotal
    );

}
