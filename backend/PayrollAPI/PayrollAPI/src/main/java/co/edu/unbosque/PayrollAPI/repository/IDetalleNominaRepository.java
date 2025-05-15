package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.DetalleNomina;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
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


}
