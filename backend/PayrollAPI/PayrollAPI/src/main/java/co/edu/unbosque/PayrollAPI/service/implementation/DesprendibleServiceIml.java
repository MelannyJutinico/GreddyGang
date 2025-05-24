package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.entity.DesprendibleView;
import co.edu.unbosque.PayrollAPI.service.interfaces.IDesprendibleService;
import org.springframework.stereotype.Service;
import co.edu.unbosque.PayrollAPI.repository.IDesprendibleRepository;
import java.util.List;

@Service
public class DesprendibleServiceIml implements IDesprendibleService {


    private final IDesprendibleRepository repository;


    public DesprendibleServiceIml(IDesprendibleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DesprendibleView> getByEmpleadoAndPeriodo(Long idEmpleado, Long idPeriodo) {
        return repository.findByIdEmpleadoAndIdPeriodo(idEmpleado, idPeriodo);
    }

    @Override
    public List<DesprendibleView> getByNominaId(Long idNomina) {
        return repository.findByIdNomina(idNomina);
    }
}
