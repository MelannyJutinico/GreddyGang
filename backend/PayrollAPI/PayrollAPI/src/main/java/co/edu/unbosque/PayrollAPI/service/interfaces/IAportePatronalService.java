package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.dto.regular.MensajeDTO;

public interface IAportePatronalService {

    MensajeDTO spGenerarAportesPatronales(Integer pnIdPeriodo);

}
