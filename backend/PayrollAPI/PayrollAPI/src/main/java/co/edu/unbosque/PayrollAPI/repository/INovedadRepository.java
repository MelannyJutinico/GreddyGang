package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.model.entity.Novedad;
import co.edu.unbosque.PayrollAPI.projection.NovedadTipoNovedadProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface INovedadRepository extends CrudRepository<Novedad, Integer> {

    @Query(
            value = "EXEC sp_agregar_novedad :pn_id_empleado, :pn_id_tipo_novedad, :pd_fecha_inicio, :pd_fecha_fin, :pn_porcentaje_pago, :vv_observacion",
            nativeQuery = true
    )
    List<Mensaje> spAgregarNovedad(
            @Param("pn_id_empleado") Integer idEmpleado,
            @Param("pn_id_tipo_novedad") Integer idTipoNovedad,
            @Param("pd_fecha_inicio") LocalDate fechaInicio,
            @Param("pd_fecha_fin") LocalDate fechaFin,
            @Param("pn_porcentaje_pago") BigDecimal porcentajePago,
            @Param("vv_observacion") String observacion
    );



    @Query(
            value = "EXEC sp_listar_novedades_periodo :pn_id_empleado, :pn_id_periodo ",
            nativeQuery = true
    )
    List<NovedadTipoNovedadProjection> spListarNovedadesDelPeriodo(
            @Param("pn_id_empleado") Integer pnIdEmpleado,   @Param("pn_id_periodo") Integer pnIdPeriodo
    );

    @Query(
            value = "EXEC sp_eliminar_novedad :idNovedad",
            nativeQuery = true
    )
    Mensaje spEliminarNovedad(
            @Param("idNovedad") Integer idNovedad
    );

}
