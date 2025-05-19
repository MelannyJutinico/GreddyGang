package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.LogIntentoModificacionNomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogIntentoModificacionNominaRepository extends JpaRepository<LogIntentoModificacionNomina, Integer> {

    @Query(value = """
        SELECT l FROM LogIntentoModificacionNomina l
        WHERE (:idNomina IS NULL OR l.idNomina = :idNomina)
          AND (:idConcepto IS NULL OR l.idConcepto = :idConcepto)
          AND (:usuario IS NULL OR l.usuario = :usuario)
          AND (:fechaInicio IS NULL OR l.fecha >= :fechaInicio)
          AND (:fechaFin IS NULL OR l.fecha <= :fechaFin)
        ORDER BY l.fecha DESC
    """)
    List<LogIntentoModificacionNomina> filtrar(
            @Param("idNomina") Integer idNomina,
            @Param("idConcepto") Integer idConcepto,
            @Param("usuario") String usuario,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );
}
