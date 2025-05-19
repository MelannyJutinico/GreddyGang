package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IAuditoriaController;
import co.edu.unbosque.PayrollAPI.service.interfaces.IAuditoriaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Controller
public class AuditoriaControllerImpl implements IAuditoriaController {

    private final IAuditoriaService auditoriaService;

    public AuditoriaControllerImpl(IAuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @Override
    @GetMapping()
    public String verAuditoria(Model model, HttpSession session) {
        return "moduloAuditoria";
    }

    @GetMapping("/estado")
    public String buscarEstadoNomina(
            @RequestParam(required = false) Integer idNomina,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String desde,
            @RequestParam(required = false) String hasta,
            Model model
    ) {
        LocalDateTime d1 = parseDateTime(desde);
        LocalDateTime d2 = parseDateTime(hasta);

        model.addAttribute("logsEstado", auditoriaService.buscarLogsEstadoNomina(
                idNomina,
                d1,
                d2,
                clean(usuario)
        ));
        return "moduloAuditoria";
    }

    @GetMapping("/modificaciones")
    public String buscarModificacionesNomina(
            @RequestParam(required = false) Integer idNomina,
            @RequestParam(required = false) Integer idConcepto,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String desde,
            @RequestParam(required = false) String hasta,
            Model model
    ) {
        LocalDateTime d1 = parseDateTime(desde);
        LocalDateTime d2 = parseDateTime(hasta);


        model.addAttribute("logsModNomina", auditoriaService.buscarLogsModificacionesNomina(
                idNomina,
                idConcepto,
                d1,
                d2,
                clean(usuario)));
        return "moduloAuditoria";
    }

    @GetMapping("/novedades")
    public String buscarLogsNovedades(
            @RequestParam(required = false) Integer idEmpleado,
            @RequestParam(required = false) Integer idTipoNovedad,
            @RequestParam(required = false) String accion,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String desde,
            @RequestParam(required = false) String hasta,
            Model model
    ) {
        LocalDateTime d1 = parseDateTime(desde);
        LocalDateTime d2 = parseDateTime(hasta);

        model.addAttribute("logsNovedad", auditoriaService.buscarLogsNovedades(idEmpleado, idTipoNovedad, d1, d2, clean(accion),clean( usuario)));
        return "moduloAuditoria";
    }


    private LocalDateTime parseDateTime(String input) {
        try {
            if (input != null && !input.isBlank()) {
                return LocalDate.parse(input).atStartOfDay(); // Si solo llega la fecha, usa hora 00:00
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String clean(String value) {
        return (value != null && !value.isBlank()) ? value : null;
    }


}
