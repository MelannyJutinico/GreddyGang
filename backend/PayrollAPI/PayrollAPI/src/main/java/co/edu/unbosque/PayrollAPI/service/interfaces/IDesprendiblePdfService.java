package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.DesprendiblePdfDocument;

import java.util.List;

public interface IDesprendiblePdfService {
    List<DesprendiblePdfDocument> listarTodos();
    List<DesprendiblePdfDocument> buscarPorEmpleado(Long idEmpleado);
    List<DesprendiblePdfDocument> buscarPorPeriodo(Long idPeriodo);
    DesprendiblePdfDocument obtenerPorId(String id);
}
