package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadEmpleadoView;
import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadTotalesPeriodoView;
import co.edu.unbosque.PayrollAPI.repository.IContabilidadEmpleadoViewRepository;
import co.edu.unbosque.PayrollAPI.repository.IContabilidadTotalesRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IContabilidadEmpleadoService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContabilidadEmpleadoServiceImpl implements IContabilidadEmpleadoService {

    private final IContabilidadTotalesRepository repoTotal;


    private final IContabilidadEmpleadoViewRepository repo;

    public ContabilidadEmpleadoServiceImpl(IContabilidadTotalesRepository repoTotal, IContabilidadEmpleadoViewRepository repo) {
        this.repoTotal = repoTotal;
        this.repo = repo;
    }

    @Override
    public List<ContabilidadEmpleadoView> consultarPorPeriodo(Integer idPeriodo) {
        return repo.findByPeriodo(idPeriodo);
    }

    @Override
    public ContabilidadTotalesPeriodoView getTotalesByPeriodo(Integer idPeriodo) {
        try {
            return repoTotal.findById(idPeriodo).orElse(null);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al obtener totales contables del periodo");
        }
    }
}
