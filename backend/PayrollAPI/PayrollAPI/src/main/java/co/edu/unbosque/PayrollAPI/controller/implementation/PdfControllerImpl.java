package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IPdfController;
import co.edu.unbosque.PayrollAPI.model.entity.DesprendiblePdfDocument;
import co.edu.unbosque.PayrollAPI.model.entity.DesprendibleView;
import co.edu.unbosque.PayrollAPI.repository.DesprendiblePdfRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IDesprendibleService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
public class PdfControllerImpl implements IPdfController {

    private final SpringTemplateEngine templateEngine;
    private final IDesprendibleService desprendibleService;
    private final DesprendiblePdfRepository pdfRepository;

    public PdfControllerImpl(SpringTemplateEngine templateEngine, IDesprendibleService desprendibleService, DesprendiblePdfRepository pdfRepository) {
        this.templateEngine = templateEngine;
        this.desprendibleService = desprendibleService;
        this.pdfRepository = pdfRepository;
    }

    @Override
    public void generarDesprendiblePdf(Long idEmpleado, Long idPeriodo, HttpServletResponse response) throws Exception {
        List<DesprendibleView> data = desprendibleService.getByEmpleadoAndPeriodo(idEmpleado, idPeriodo);

        Context context = new Context();
        context.setVariable("desprendible", data);

        String html = templateEngine.process("desprendible", context);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(html, null);
        builder.toStream(baos);
        builder.run();

        byte[] pdfBytes = baos.toByteArray();

        // Guardar en MongoDB
        DesprendiblePdfDocument doc = new DesprendiblePdfDocument();
        doc.setIdEmpleado(idEmpleado);
        doc.setIdPeriodo(idPeriodo);
        doc.setContenidoPdf(pdfBytes);
        doc.setNombreArchivo("desprendible_" + idEmpleado + "_" + idPeriodo + ".pdf");
        doc.setFechaGeneracion(java.time.LocalDateTime.now().toString());

        pdfRepository.save(doc);

        // Descargar

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=desprendible.pdf");
        response.getOutputStream().write(baos.toByteArray());
    }

    @Override
    public String listarPdfs(Model model) {
        model.addAttribute("pdfs", pdfRepository.findAll());
        return "listaDesprendibles"; // tu vista con la tabla
    }

    @Override
    public void descargarPdf(
            @RequestParam("id") String id,
            HttpServletResponse response) throws Exception {

        DesprendiblePdfDocument doc = pdfRepository.findById(id).orElse(null);
        if (doc == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "PDF no encontrado");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + doc.getNombreArchivo());
        response.getOutputStream().write(doc.getContenidoPdf());
    }


}
