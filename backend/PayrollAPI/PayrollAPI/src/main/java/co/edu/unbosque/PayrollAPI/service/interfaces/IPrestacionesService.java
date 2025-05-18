package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.CesantiaPagadaDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.InteresCesantiaDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PrimaDTO;

import java.util.List;

public interface IPrestacionesService {
    MensajeDTO generarPrima(Integer idPeriodo);
    MensajeDTO generarInteresesCesantias(Integer idPeriodo);
    MensajeDTO registrarCesantias(Integer idPeriodo);

    List<PrimaDTO> obtenerPrimasPorPeriodo(Integer idPeriodo);
    List<InteresCesantiaDTO> obtenerInteresesCesantiasPorPeriodo(Integer idPeriodo);
    List<CesantiaPagadaDTO> obtenerCesantiasPagadasPorPeriodo(Integer idPeriodo);


}
