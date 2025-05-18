package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;

public interface INominaService {


    String spGenerarNominaMasiva(Integer pdIdPeriodo);
    MensajeDTO liquidarPeriodo(Integer idPeriodo);

}
