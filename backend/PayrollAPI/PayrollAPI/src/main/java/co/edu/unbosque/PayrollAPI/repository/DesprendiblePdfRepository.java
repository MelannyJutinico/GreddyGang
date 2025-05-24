package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.DesprendiblePdfDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DesprendiblePdfRepository extends MongoRepository<DesprendiblePdfDocument, String> {
    List<DesprendiblePdfDocument> findByIdEmpleado(Long idEmpleado);
    List<DesprendiblePdfDocument> findByIdPeriodo(Long idPeriodo);

}

