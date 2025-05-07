package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.entity.PeriodoNomina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IPeriodoNominaRepository extends CrudRepository<PeriodoNomina, Integer> {

    @Query(
            value = "EXEC sp_crear_periodo :pd_fecha_inicio, :pd_fecha_fin, :vv_descripcion",
            nativeQuery = true
    )
    Mensaje spCrearPeriodo(
            @Param("pd_fecha_inicio") LocalDate pdFechaInicio,
            @Param("pd_fecha_fin") LocalDate pdFechaFin,
            @Param("vv_descripcion") String vvDescripcion
            );

    @Query(
            value = "EXEC sp_cambiar_estado_periodo :pn_id_periodo, :vv_nuevo_estado",
            nativeQuery = true
    )
    Mensaje spCambiarEstadoPeriodo(
            @Param("pn_id_periodo ") Integer pnIdPeriodo ,
            @Param("vv_nuevo_estado ") String vvNuevoEstado
            );


}
