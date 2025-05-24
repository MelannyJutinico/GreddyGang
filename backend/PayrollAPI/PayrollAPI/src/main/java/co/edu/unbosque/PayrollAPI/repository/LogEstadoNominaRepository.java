package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.LogEstadoNomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogEstadoNominaRepository extends JpaRepository<LogEstadoNomina, Integer> {

    @Query(value = """
        SELECT l FROM LogEstadoNomina l
        WHERE (:idNomina IS NULL OR l.idNomina = :idNomina)
          AND (:estadoAnterior IS NULL OR l.estadoAnterior = :estadoAnterior)
          AND (:estadoNuevo IS NULL OR l.estadoNuevo = :estadoNuevo)
          AND (:usuario IS NULL OR l.usuario = :usuario)
          AND (:fechaInicio IS NULL OR l.fechaLog >= :fechaInicio)
          AND (:fechaFin IS NULL OR l.fechaLog <= :fechaFin)
        ORDER BY l.fechaLog DESC
    """)
    List<LogEstadoNomina> filtrar(
            @Param("idNomina") Integer idNomina,
            @Param("estadoAnterior") String estadoAnterior,
            @Param("estadoNuevo") String estadoNuevo,
            @Param("usuario") String usuario,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );
}
