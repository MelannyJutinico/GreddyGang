package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.ProvisionPrestacionPeriodoView;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;

import java.util.List;

public interface IProvisionPrestacionService {

    MensajeDTO spGenerarProvisiones(Integer pnIdPeriodo);
    List<ProvisionPrestacionPeriodoView> getProvisionesByPeriodoAndEmployee(Integer idPeriodo, Integer idEmpleado);

}
