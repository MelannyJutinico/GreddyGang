package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.CesantiaPagadaDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.InteresCesantiaDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PrimaDTO;
import co.edu.unbosque.PayrollAPI.repository.IPrestacionesRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IPrestacionesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestacionesServiceImpl implements IPrestacionesService {

  private final IPrestacionesRepository repo;

    public PrestacionesServiceImpl(IPrestacionesRepository repo) {
        this.repo = repo;
    }

    @Override
    public MensajeDTO generarPrima(Integer idPeriodo) {
        return repo.spGenerarPrimaServicios(idPeriodo);
    }

    @Override
    public MensajeDTO generarInteresesCesantias(Integer idPeriodo) {
        return repo.spGenerarInteresesCesantias(idPeriodo);
    }

    @Override
    public MensajeDTO registrarCesantias(Integer idPeriodo) {
        return repo.spRegistrarCesantiasPagadas(idPeriodo);
    }
    @Override
    public List<PrimaDTO> obtenerPrimasPorPeriodo(Integer idPeriodo) {
        return repo.vwPrimasPorPeriodo(idPeriodo);
    }

    @Override
    public List<InteresCesantiaDTO> obtenerInteresesCesantiasPorPeriodo(Integer idPeriodo) {
        return repo.vwInteresesCesantiasPorPeriodo(idPeriodo);
    }

    @Override
    public List<CesantiaPagadaDTO> obtenerCesantiasPagadasPorPeriodo(Integer idPeriodo) {
        return repo.vwCesantiasPagadasPorPeriodo(idPeriodo);
    }


}
