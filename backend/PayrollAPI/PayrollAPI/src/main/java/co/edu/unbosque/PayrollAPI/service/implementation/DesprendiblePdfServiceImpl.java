package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.entity.DesprendiblePdfDocument;
import co.edu.unbosque.PayrollAPI.repository.DesprendiblePdfRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IDesprendiblePdfService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesprendiblePdfServiceImpl implements IDesprendiblePdfService {

    private final DesprendiblePdfRepository repository;

    public DesprendiblePdfServiceImpl(DesprendiblePdfRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DesprendiblePdfDocument> listarTodos() {
        return repository.findAll();
    }

    @Override
    public List<DesprendiblePdfDocument> buscarPorEmpleado(Long idEmpleado) {
        return repository.findByIdEmpleado(idEmpleado);
    }

    @Override
    public List<DesprendiblePdfDocument> buscarPorPeriodo(Long idPeriodo) {
        return repository.findByIdPeriodo(idPeriodo);
    }

    @Override
    public DesprendiblePdfDocument obtenerPorId(String id) {
        return repository.findById(id).orElse(null);
    }
}
