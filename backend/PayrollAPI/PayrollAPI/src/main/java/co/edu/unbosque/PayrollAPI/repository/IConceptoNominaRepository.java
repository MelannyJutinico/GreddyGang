package co.edu.unbosque.PayrollAPI.repository;
import co.edu.unbosque.PayrollAPI.model.entity.ConceptoNomina;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.projection.ConceptoHoraExtraProjection;
import co.edu.unbosque.PayrollAPI.projection.ConceptoTipoConceptoProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    @Modifying
    @Query(
            value = "EXEC sp_agregar_horas_extra :pn_id_nomina, :pn_id_empleado, :pn_id_concepto, :vn_cantidad_horas",
            nativeQuery = true
    )
    void spAgregarHorasExtras(
            @Param("pn_id_nomina") Integer pnIdNomina,
            @Param("pn_id_empleado") Integer pnIdEmpleado,
            @Param("pn_id_concepto") Integer pnIdConcepto,
            @Param("vn_cantidad_horas") BigDecimal vnCantidadHoras
    );




}
