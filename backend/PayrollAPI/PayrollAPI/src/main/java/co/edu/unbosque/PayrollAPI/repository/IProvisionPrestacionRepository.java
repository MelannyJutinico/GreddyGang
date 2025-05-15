package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.model.entity.ProvisionPrestacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvisionPrestacionRepository extends CrudRepository<ProvisionPrestacion, Integer> {

    @Query(
            value = "EXEC sp_generar_provisiones :pn_id_periodo",
            nativeQuery = true
    )
    Mensaje spGenerarProvisiones(
            @Param("pn_id_periodo ") Integer pnIdPeriodo
    );

}
