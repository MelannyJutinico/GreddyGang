package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.Empleado;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IEmpleadoRepository extends CrudRepository<Empleado, Integer> {


    @Query(
            value = "EXEC sp_buscar_empleado_por_id :pnIdEmpleado",
            nativeQuery = true
    )
    Empleado spBuscarEmpleadoPorId(
            @Param("pn_id_empleado") Integer pnIdEmpleado
    );

    @Modifying
    @Query(value = "EXEC sp_inactivar_empleado :pn_id_empleado", nativeQuery = true)
    void spInactivarEmpleado(@Param("pn_id_empleado") Integer idEmpleado);


    @Query(
            value = "SELECT * FROM vw_empleados_inactivos",
            nativeQuery = true
    )
    List<Empleado> vwEmpleadosInactivos();

    @Query(
            value = "SELECT * FROM vw_empleados_activos",
            nativeQuery = true
    )
    List<Empleado> vwEmpleadosActivos();


    @Query(
            value = "SELECT * FROM vw_empleados",
            nativeQuery = true
    )
    List<Empleado> vwEmpleados();

    @Modifying
    @Transactional
    @Query(
            value = "EXEC sp_crear_empleado :pn_nombre, :pn_cargo, :pn_departamento, :pn_salario_base, :pn_fecha_ingreso, :pn_nivel_riesgo, :pn_id_tipo_contrato",
            nativeQuery = true
    )
    void crearEmpleado(
            @Param("pn_nombre") String nombre,
            @Param("pn_cargo") String cargo,
            @Param("pn_departamento") String departamento,
            @Param("pn_salario_base") BigDecimal salario,
            @Param("pn_fecha_ingreso") LocalDate ingreso,
            @Param("pn_nivel_riesgo") Integer nivelRiesgo,
            @Param("pn_id_tipo_contrato") Integer tipoContrato
    );


}
