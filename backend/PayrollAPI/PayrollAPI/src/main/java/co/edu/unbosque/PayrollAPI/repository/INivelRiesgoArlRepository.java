package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.NivelRiesgoArl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INivelRiesgoArlRepository extends JpaRepository< NivelRiesgoArl, Integer> {

}
