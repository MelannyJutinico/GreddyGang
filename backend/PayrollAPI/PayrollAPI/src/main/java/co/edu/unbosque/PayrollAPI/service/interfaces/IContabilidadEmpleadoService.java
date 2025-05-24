package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.AportePatronalDTO;
import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadEmpleadoView;
import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadTotalesPeriodoView;

import java.util.List;

public interface IContabilidadEmpleadoService {
    List<ContabilidadEmpleadoView> consultarPorPeriodo(Integer idPeriodo);
    ContabilidadTotalesPeriodoView getTotalesByPeriodo(Integer idPeriodo);
    List<AportePatronalDTO> obtenerAportesPorPeriodo(Integer idPeriodo);
}
