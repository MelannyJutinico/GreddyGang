package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.entity.TipoContrato;
import co.edu.unbosque.PayrollAPI.repository.ITipoContratoRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.ITipoContratoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoContratoServiceImpl implements ITipoContratoService {

    private final ITipoContratoRepository repository;

    public TipoContratoServiceImpl(ITipoContratoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipoContrato> obtenerTodos() {
        return repository.findAll();
    }
}
