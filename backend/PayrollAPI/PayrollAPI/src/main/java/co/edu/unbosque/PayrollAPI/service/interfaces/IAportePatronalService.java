package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.AportePatronalPeriodoView;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;

import java.util.List;

public interface IAportePatronalService {

    MensajeDTO spGenerarAportesPatronales(Integer pnIdPeriodo);
    List<AportePatronalPeriodoView> getAportesByPeriodoAndEmployee(Integer idPeriodo, Integer idEmpleado);
}
