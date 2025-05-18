package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IPeriodoNominaController;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PeriodoNominaDTO;
import co.edu.unbosque.PayrollAPI.service.interfaces.IPeriodoNominaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PeriodoNominaControllerImpl implements IPeriodoNominaController {

    private final IPeriodoNominaService service;

    public PeriodoNominaControllerImpl(IPeriodoNominaService periodoNominaService) {
        this.service = periodoNominaService;
    }

    @Override
    public String spCrearPeriodo(String mesSeleccionado, String vvDescripcion, Model model, RedirectAttributes redirectAttributes) {

        try {
            MensajeDTO mensajeDTO = service.spCrearPeriodo(mesSeleccionado, vvDescripcion);
                redirectAttributes.addFlashAttribute("mensaje", mensajeDTO.getMensaje());
                redirectAttributes.addFlashAttribute("estado", mensajeDTO.getEstado());

        } catch (DataBaseException e) {
            redirectAttributes.addFlashAttribute("mensaje", e.getMessage());
            redirectAttributes.addFlashAttribute("estado", "ERROR");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("estado", "ERROR");
            redirectAttributes.addFlashAttribute("mensaje", "Ocurrió un error inesperado al crear el periodo.");
        }

        return "redirect:/periodo-nomina/activo";
    }

    @Override
    public String spCambiarEstadoPeriodo(Integer pnIdPeriodo, String vvNuevoEstado, Model model) {
        return "";
    }

    @Override
    public String mostrarPeriodosActivos(Model model) {
        List<PeriodoNominaDTO> periodos = service.vwPeriodoNominaActivo();
        model.addAttribute("periodos", periodos);
        return "moduloPeriodo";
    }

    @Override
    public String listarPeriodosCerrados(Model model, @ModelAttribute("mensaje") String mensaje, @ModelAttribute("error") String error
    ) {
        List<PeriodoNominaDTO> periodos = service.vwPeriodosNominaCerrados();
        model.addAttribute("periodos", periodos);

        if (mensaje != null && !mensaje.isBlank()) {
            model.addAttribute("mensaje", mensaje);
            model.addAttribute("estado", "OK"); // Esto lo usas en el th:classappend
        }

        if (error != null && !error.isBlank()) {
            model.addAttribute("mensaje", error);
            model.addAttribute("estado", "ERROR");
        }


        return "moduloPeriodosCerrados";
    }


}
