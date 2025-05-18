package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IPrestacionesController;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PeriodoNominaDTO;
import co.edu.unbosque.PayrollAPI.service.interfaces.IPeriodoNominaService;
import co.edu.unbosque.PayrollAPI.service.interfaces.IPrestacionesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PrestacionesControllerImpl implements IPrestacionesController {


    private final IPrestacionesService service;
    private final IPeriodoNominaService periodoService;

    public PrestacionesControllerImpl(IPrestacionesService service, IPeriodoNominaService periodoService) {
        this.service = service;
        this.periodoService = periodoService;
    }


    @Override
    public String verModuloPrestaciones(
            @RequestParam(required = false) Integer idPeriodo,
            Model model,
            @ModelAttribute("mensaje") String mensaje,
            @ModelAttribute("estado") String estado
    ) {
        List<PeriodoNominaDTO> periodosActivos = periodoService.vwPeriodoNominaActivo();
        model.addAttribute("periodos", periodosActivos);

        // Cargar registros solo si se ha seleccionado un período
        if (idPeriodo != null) {
            model.addAttribute("idPeriodoSeleccionado", idPeriodo);
            model.addAttribute("primas", service.obtenerPrimasPorPeriodo(idPeriodo));
            model.addAttribute("intereses", service.obtenerInteresesCesantiasPorPeriodo(idPeriodo));
            model.addAttribute("cesantias", service.obtenerCesantiasPagadasPorPeriodo(idPeriodo));
        }

        if (mensaje != null && !mensaje.isBlank()) model.addAttribute("mensaje", mensaje);
        if (estado != null && !estado.isBlank()) model.addAttribute("estado", estado);

        return "moduloPrestaciones";
    }


    @Override
    public String generarPrestacion(@RequestParam Integer idPeriodo, @RequestParam String accion, RedirectAttributes redirect) {
        try {
            MensajeDTO resultado;
            switch (accion) {
                case "cesantias":
                    resultado = service.registrarCesantias(idPeriodo);
                    break;
                case "prima":
                    resultado = service.generarPrima(idPeriodo);
                    break;
                case "intereses":
                    resultado = service.generarInteresesCesantias(idPeriodo);
                    break;
                default:
                    throw new IllegalArgumentException("Acción no válida");
            }
            redirect.addFlashAttribute("mensaje", resultado.getMensaje());
            redirect.addFlashAttribute("estado", resultado.getEstado());
        }  catch (Exception e) {
        redirect.addFlashAttribute("mensaje", getRootCauseMessage(e));
        redirect.addFlashAttribute("estado", "ERROR");
    }


        return "redirect:/prestaciones";
    }

    private String getRootCauseMessage(Throwable e) {
        Throwable cause = e;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause.getMessage();
    }


}
