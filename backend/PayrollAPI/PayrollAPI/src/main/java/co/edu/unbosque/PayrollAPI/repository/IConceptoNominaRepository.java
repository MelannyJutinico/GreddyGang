package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.entity.ConceptoNomina;
import co.edu.unbosque.PayrollAPI.projection.ConceptoHoraExtraProjection;
import co.edu.unbosque.PayrollAPI.projection.ConceptoTipoConceptoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

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

}
