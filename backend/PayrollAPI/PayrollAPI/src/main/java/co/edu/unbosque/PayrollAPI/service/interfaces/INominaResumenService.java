package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.NominaResumenView;

import java.util.List;

public interface INominaResumenService {
    List<NominaResumenView> consultarPorPeriodo(String periodo);
}
