package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.entity.LogEstadoNomina;
import co.edu.unbosque.PayrollAPI.model.entity.LogIntentoModificacionNomina;
import co.edu.unbosque.PayrollAPI.model.entity.LogNovedad;
import co.edu.unbosque.PayrollAPI.repository.LogEstadoNominaRepository;
import co.edu.unbosque.PayrollAPI.repository.LogIntentoModificacionNominaRepository;
import co.edu.unbosque.PayrollAPI.repository.LogNovedadRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IAuditoriaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements IAuditoriaService {

    private final LogEstadoNominaRepository estadoRepo;
    private final LogIntentoModificacionNominaRepository modNominaRepo;
    private final LogNovedadRepository novedadRepo;

    public AuditoriaServiceImpl(LogEstadoNominaRepository estadoRepo,
                                LogIntentoModificacionNominaRepository modNominaRepo,
                                LogNovedadRepository novedadRepo) {
        this.estadoRepo = estadoRepo;
        this.modNominaRepo = modNominaRepo;
        this.novedadRepo = novedadRepo;
    }

    @Override
    public List<LogEstadoNomina> buscarLogsEstadoNomina(Integer idNomina, LocalDateTime desde, LocalDateTime hasta, String usuario) {
        return estadoRepo.filtrar(idNomina, null, null, usuario, desde, hasta);
    }

    @Override
    public List<LogIntentoModificacionNomina> buscarLogsModificacionesNomina(Integer idNomina, Integer idConcepto, LocalDateTime desde, LocalDateTime hasta, String usuario) {
        return modNominaRepo.filtrar(idNomina, idConcepto, usuario, desde, hasta);
    }

    @Override
    public List<LogNovedad> buscarLogsNovedades(Integer idEmpleado, Integer idTipoNovedad, LocalDateTime desde, LocalDateTime hasta, String accion, String usuario) {
        return novedadRepo.filtrar(idEmpleado, idTipoNovedad, accion, usuario, desde, hasta);
    }
}
