package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IPeriodoNominaController;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PeriodoNominaDTO;
import co.edu.unbosque.PayrollAPI.service.interfaces.IPeriodoNominaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        MensajeDTO mensajeDTO = service.spCrearPeriodo(mesSeleccionado, vvDescripcion);

        redirectAttributes.addFlashAttribute("mensaje", mensajeDTO.getMensaje());
        redirectAttributes.addFlashAttribute("estado", mensajeDTO.getEstado());

        return "redirect:/periodo-nomina/listar";
    }

    @Override
    public String spCambiarEstadoPeriodo(Integer pnIdPeriodo, String vvNuevoEstado, Model model) {
        return "";
    }

    @Override
    public String mostrarPeriodosActivos(Model model) {
        List<PeriodoNominaDTO> periodos = service.vwPeriodoNomina();
        model.addAttribute("periodos", periodos);
        return "moduloPeriodo";
    }

}
