package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.TipoContrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoContratoRepository extends JpaRepository<TipoContrato, Integer> {
}
