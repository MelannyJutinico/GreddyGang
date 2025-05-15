package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.entity.NominaResumenView;
import co.edu.unbosque.PayrollAPI.repository.INominaResumenRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.INominaResumenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NominaResumenServiceImpl implements INominaResumenService {

    private final INominaResumenRepository repository;

    public NominaResumenServiceImpl(INominaResumenRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NominaResumenView> consultarPorPeriodo(String periodo) {
        return repository.findByPeriodo(periodo);
    }
}
