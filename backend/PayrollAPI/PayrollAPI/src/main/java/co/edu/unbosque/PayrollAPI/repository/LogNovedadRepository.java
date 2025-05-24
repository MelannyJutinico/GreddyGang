package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.LogNovedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogNovedadRepository extends JpaRepository<LogNovedad, Integer> {

    @Query(value = """
        SELECT l FROM LogNovedad l
        WHERE (:idEmpleado IS NULL OR l.idEmpleado = :idEmpleado)
          AND (:idTipoNovedad IS NULL OR l.idTipoNovedad = :idTipoNovedad)
          AND (:accion IS NULL OR l.accion = :accion)
          AND (:usuario IS NULL OR l.usuario = :usuario)
          AND (:fechaInicio IS NULL OR l.fechaLog >= :fechaInicio)
          AND (:fechaFin IS NULL OR l.fechaLog <= :fechaFin)
        ORDER BY l.fechaLog DESC
    """)
    List<LogNovedad> filtrar(
            @Param("idEmpleado") Integer idEmpleado,
            @Param("idTipoNovedad") Integer idTipoNovedad,
            @Param("accion") String accion,
            @Param("usuario") String usuario,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );
}
