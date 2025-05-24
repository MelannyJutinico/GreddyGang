package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadTotalesPeriodoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContabilidadTotalesRepository extends JpaRepository<ContabilidadTotalesPeriodoView, Integer> {
}
