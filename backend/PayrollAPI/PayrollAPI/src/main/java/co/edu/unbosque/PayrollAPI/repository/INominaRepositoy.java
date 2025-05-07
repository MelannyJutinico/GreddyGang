package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.entity.Nomina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface INominaRepositoy extends CrudRepository<Nomina, Integer> {

    @Query(
            value = "EXEC sp_crear_nomina :pn_id_empleado, :pn_id_periodo, :pd_fecha_liquidacion",
            nativeQuery = true
    )
    Mensaje spCrearNomina(
            @Param("pn_id_empleado") Integer pnIdEmpleado,
            @Param("pn_id_periodo") Integer pnIdPeriodo,
            @Param("pd_fecha_liquidacion") LocalDate pdFechaLiquidacion
    );

    @Query(
            value = "EXEC sp_generar_nomina_masiva :pn_id_periodo",
            nativeQuery = true
    )
    Mensaje spGenerarNominaMasiva(
            @Param("pn_id_periodo") Integer pnIdPeriodo
    );

    @Query(
            value = "EXEC sp_liquidar_nomina :pn_id_periodo",
            nativeQuery = true
    )
    Mensaje spLiquidarNomina(
            @Param("pn_id_periodo ") Integer pnIdPeriodo
    );

}
