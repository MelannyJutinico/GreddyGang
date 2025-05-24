package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.DesprendibleView;

import java.util.List;

public interface IDesprendibleService {

    List<DesprendibleView> getByEmpleadoAndPeriodo(Long idEmpleado, Long idPeriodo);
    List<DesprendibleView> getByNominaId(Long idNomina);
}
