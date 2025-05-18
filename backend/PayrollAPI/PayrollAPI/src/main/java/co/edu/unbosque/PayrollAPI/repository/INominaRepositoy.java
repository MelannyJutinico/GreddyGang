package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.model.entity.Nomina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface INominaRepositoy extends CrudRepository<Nomina, Integer> {


    @Query(
            value = "EXEC sp_generar_nomina_masiva :pn_id_periodo",
            nativeQuery = true
    )
    List<Mensaje> spGenerarNominaMasiva(
            @Param("pn_id_periodo") Integer pnIdPeriodo
    );


    @Query(value = "EXEC sp_liquidar_periodo :pn_id_periodo", nativeQuery = true)
    List<Object[]> spLiquidarPeriodo(@Param("pn_id_periodo") Integer idPeriodo);

    @Query(value = "SELECT dbo.fn_existe_nomina_por_periodo(:periodoId)", nativeQuery = true)
    boolean existeNominaParaPeriodo(@Param("periodoId") Integer periodoId);

    @Query(
            value = "SELECT id_nomina FROM fn_obtener_nomina_empleado_periodo(:idEmpleado, :idPeriodo)",
            nativeQuery = true
    )
    Integer obtenerIdNominaPorEmpleadoYPeriodo(
            @Param("idEmpleado") Integer idEmpleado,
            @Param("idPeriodo") Integer idPeriodo
    );


}
