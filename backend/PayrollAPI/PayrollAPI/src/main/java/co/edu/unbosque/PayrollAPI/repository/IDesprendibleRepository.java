package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.DesprendibleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDesprendibleRepository extends JpaRepository<DesprendibleView, Long> {
    List<DesprendibleView> findByIdNomina(Long idNomina);
    List<DesprendibleView> findByIdEmpleadoAndIdPeriodo(Long idEmpleado, Long idPeriodo);
}