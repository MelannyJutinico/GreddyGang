package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.ConceptoNominaDTO;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;


import java.math.BigDecimal;
import java.util.List;

public interface IConceptoNominaService {

    List<ConceptoNominaDTO> vwConceptosDevengadosDeduccion();

    List<ConceptoNominaDTO> vwConceptosHorasExtra();

    MensajeDTO spAgregarHorasExtras(Integer idPeriodo, Integer pdIdEmpleado, Integer pnIdConcepto, BigDecimal vnCantidadHoras);

}
