package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.AportePatronalDTO;
import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadEmpleadoView;
import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadTotalesPeriodoView;
import co.edu.unbosque.PayrollAPI.repository.IAportePatronalViewRepository;
import co.edu.unbosque.PayrollAPI.repository.IContabilidadEmpleadoViewRepository;
import co.edu.unbosque.PayrollAPI.repository.IContabilidadTotalesRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IContabilidadEmpleadoService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContabilidadEmpleadoServiceImpl implements IContabilidadEmpleadoService {

    private final IContabilidadTotalesRepository repoTotal;
    private final IAportePatronalViewRepository aportePatronalRepo;

    private final IContabilidadEmpleadoViewRepository repo;

    public ContabilidadEmpleadoServiceImpl(IContabilidadTotalesRepository repoTotal, IAportePatronalViewRepository aportePatronalRepo, IContabilidadEmpleadoViewRepository repo) {
        this.repoTotal = repoTotal;
        this.aportePatronalRepo = aportePatronalRepo;
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

    @Override
    public List<AportePatronalDTO> obtenerAportesPorPeriodo(Integer idPeriodo) {
        return aportePatronalRepo.findAportesByPeriodo(idPeriodo).stream().map(obj -> {
            AportePatronalDTO dto = new AportePatronalDTO();
            dto.setIdEmpleado(obj[1] != null ? ((Number) obj[1]).intValue() : null);
            dto.setNombreEmpleado((String) obj[2]);
            dto.setTipoContrato((String) obj[3]);
            dto.setNivelRiesgo((String) obj[4]);
            dto.setSalarioBase((BigDecimal) obj[5]);
            dto.setIdPeriodo(obj[5] != null ? ((Number) obj[6]).intValue() : null);
            dto.setDescripcionPeriodo((String) obj[7]);
            dto.setTipoAporte((String) obj[8]);
            dto.setCantidad((BigDecimal) obj[9]);
            dto.setFechaLiquidacion(obj[10] != null ? ((java.sql.Date) obj[10]).toLocalDate() : null);
            return dto;
        }).collect(Collectors.toList());
    }
}
