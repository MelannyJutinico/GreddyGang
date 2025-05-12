package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.dto.regular.PeriodoNominaDTO;

import java.util.List;

public interface IPeriodoNominaService {

    MensajeDTO spCrearPeriodo(String mesSeleccionado, String vvDescripcion);

    MensajeDTO spCambiarEstadoPeriodo(Integer pnIdPeriodo, String vvNuevoEstado);

    PeriodoNominaDTO spBuscarPeriodoPorId(Integer pnIdPeriodo);

    List<PeriodoNominaDTO> vwPeriodoNomina();

}
