package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.model.entity.PeriodoNomina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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


    @Query(
            value = "EXEC sp_buscar_periodo_por_id :pn_id_periodo ",
            nativeQuery = true
    )
    PeriodoNomina spBuscarPeriodoPorId(
            @Param("pn_id_periodo") Integer pnIdPeriodo
    );


    @Query(
            value = "SELECT * FROM vw_periodo_nomina",
            nativeQuery = true
    )
    List<PeriodoNomina> vwPeriodoNomina();





}
