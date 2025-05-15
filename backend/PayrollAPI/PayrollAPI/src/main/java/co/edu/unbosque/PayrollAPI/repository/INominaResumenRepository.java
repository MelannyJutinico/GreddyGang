package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.NominaResumenView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INominaResumenRepository extends JpaRepository<NominaResumenView, Long> {
    List<NominaResumenView> findByPeriodo(String periodo);
}
