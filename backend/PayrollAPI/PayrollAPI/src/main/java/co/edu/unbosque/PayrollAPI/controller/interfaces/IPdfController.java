package co.edu.unbosque.PayrollAPI.controller.interfaces;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IPdfController {

    @GetMapping("/nomina/pdf")
    void generarDesprendiblePdf(@RequestParam("idEmpleado") Long idEmpleado,
                                @RequestParam("idPeriodo") Long idPeriodo,
                                HttpServletResponse response) throws Exception;

    @GetMapping("/nomina/pdf/lista")
    String listarPdfs(Model model);

    @GetMapping("/nomina/pdf/descargar")
    void descargarPdf(
            @RequestParam("id") String id,
            HttpServletResponse response) throws Exception;
}
