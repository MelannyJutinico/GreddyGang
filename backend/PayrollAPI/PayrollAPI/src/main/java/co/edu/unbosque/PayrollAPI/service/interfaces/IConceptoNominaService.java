package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.ConceptoNominaDTO;

import java.util.List;

public interface IConceptoNominaService {

    List<ConceptoNominaDTO> vwConceptosDevengadosDeduccion();

    List<ConceptoNominaDTO> vwConceptosHorasExtra();

}
