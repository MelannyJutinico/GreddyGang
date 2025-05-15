package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.AportePatronal;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAportePatronalRepository extends CrudRepository<AportePatronal, Integer> {

    @Query(
            value = "EXEC sp_generar_aportes_patronales :pn_id_periodo",
            nativeQuery = true
    )
    Mensaje spGenerarAportesPatronales(
            @Param("pn_id_periodo") Integer pnIdPeriodo
    );

}
