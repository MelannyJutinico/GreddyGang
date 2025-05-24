package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadEmpleadoView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IContabilidadEmpleadoViewRepository extends CrudRepository<ContabilidadEmpleadoView, Integer> {

    @Query(value = "SELECT * FROM vw_contabilidad_empleado WHERE id_periodo = :idPeriodo", nativeQuery = true)
    List<ContabilidadEmpleadoView> findByPeriodo(Integer idPeriodo);
}
