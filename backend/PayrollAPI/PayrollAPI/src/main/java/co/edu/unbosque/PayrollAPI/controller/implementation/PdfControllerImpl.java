package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IPdfController;
import co.edu.unbosque.PayrollAPI.model.entity.DesprendibleView;
import co.edu.unbosque.PayrollAPI.service.interfaces.IDesprendibleService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
public class PdfControllerImpl implements IPdfController {

    private final SpringTemplateEngine templateEngine;
    private final IDesprendibleService desprendibleService;

    public PdfControllerImpl(SpringTemplateEngine templateEngine, IDesprendibleService desprendibleService) {
        this.templateEngine = templateEngine;
        this.desprendibleService = desprendibleService;
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

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=desprendible.pdf");
        response.getOutputStream().write(baos.toByteArray());
    }
}
