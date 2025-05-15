package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.ConceptoNominaDTO;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;


import java.math.BigDecimal;
import java.util.List;

public interface IConceptoNominaService {

    List<ConceptoNominaDTO> vwConceptosDevengadosDeduccion();

    List<ConceptoNominaDTO> vwConceptosHorasExtra();

    MensajeDTO spAgregarConcepto(Integer pnIdNomina, Integer pnIdConcepto, BigDecimal pnValorTotal);

    MensajeDTO spAgregarHorasExtras(Integer pnIdNomina, Integer pdIdEmpleado, Integer pnIdConcepto, BigDecimal vnCantidadHoras);

}
