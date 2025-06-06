package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PeriodoNominaDTO;

import java.util.List;

public interface IPeriodoNominaService {

    MensajeDTO spCrearPeriodo(String mesSeleccionado, String vvDescripcion);
    PeriodoNominaDTO spBuscarPeriodoPorId(Integer pnIdPeriodo);
    List<PeriodoNominaDTO> vwPeriodoNominaActivo();
    List<PeriodoNominaDTO> vwPeriodosNominaCerrados();

}
