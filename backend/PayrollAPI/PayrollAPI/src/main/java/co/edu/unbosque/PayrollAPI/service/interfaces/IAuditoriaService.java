package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.LogEstadoNomina;
import co.edu.unbosque.PayrollAPI.model.entity.LogIntentoModificacionNomina;
import co.edu.unbosque.PayrollAPI.model.entity.LogNovedad;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IAuditoriaService {
    List<LogEstadoNomina> buscarLogsEstadoNomina(Integer idNomina, LocalDateTime desde, LocalDateTime hasta, String usuario);
    List<LogIntentoModificacionNomina> buscarLogsModificacionesNomina(Integer idNomina, Integer idConcepto, LocalDateTime desde, LocalDateTime hasta, String usuario);
    List<LogNovedad> buscarLogsNovedades(Integer idEmpleado, Integer idTipoNovedad, LocalDateTime desde, LocalDateTime hasta, String accion, String usuario);
}
