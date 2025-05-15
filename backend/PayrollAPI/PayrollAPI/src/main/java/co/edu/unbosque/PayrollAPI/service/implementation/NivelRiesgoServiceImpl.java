package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.entity.NivelRiesgoArl;
import co.edu.unbosque.PayrollAPI.repository.INivelRiesgoArlRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.INivelRiesgoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelRiesgoServiceImpl implements INivelRiesgoService {

    private final INivelRiesgoArlRepository repository;

    public NivelRiesgoServiceImpl(INivelRiesgoArlRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NivelRiesgoArl> obtenerTodos() {
        return repository.findAll();
    }
}
