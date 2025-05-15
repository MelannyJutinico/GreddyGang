package co.edu.unbosque.PayrollAPI.repository;


import co.edu.unbosque.PayrollAPI.model.entity.ProvisionPrestacionPeriodoView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProvisionesViewRepository extends CrudRepository<ProvisionPrestacionPeriodoView, Long> {

    @Query(value = "SELECT * FROM vw_provisiones_prestaciones_periodo WHERE id_periodo = :idPeriodo AND id_empleado = :idEmpleado", nativeQuery = true)
    List<ProvisionPrestacionPeriodoView> findProvisionesByPeriodoAndEmpleado(@Param("idPeriodo") Integer idPeriodo, @Param("idEmpleado") Integer idEmpleado);

}