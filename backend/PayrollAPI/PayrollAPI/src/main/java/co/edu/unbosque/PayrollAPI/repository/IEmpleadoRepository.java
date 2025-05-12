package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.entity.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmpleadoRepository extends CrudRepository<Empleado, Integer> {


    @Query(
            value = "EXEC sp_buscar_empleado_por_id :pnIdEmpleado",
            nativeQuery = true
    )
    Empleado spBuscarEmpleadoPorId(
            @Param("pn_id_empleado") Integer pnIdEmpleado
    );



    @Query(
            value = "SELECT * FROM vw_empleados_activos",
            nativeQuery = true
    )
    List<Empleado> vwEmpleadosActivos();


}
