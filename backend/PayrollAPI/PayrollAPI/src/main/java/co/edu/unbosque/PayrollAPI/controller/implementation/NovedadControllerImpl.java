package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.INovedadController;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.service.interfaces.INovedadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class NovedadControllerImpl implements INovedadController {

    private final INovedadService novedadService;

    public NovedadControllerImpl(INovedadService novedadService) {
        this.novedadService = novedadService;
    }


    @Override
    public String spAgregarNovedad(Integer idEmpleado, Integer idTipoNovedad, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion, Integer idPeriodo, Model model, RedirectAttributes redirectAttributes) {
        try {
            MensajeDTO mensaje = novedadService.spAgregarNovedad(idEmpleado, idTipoNovedad, fechaInicio, fechaFin, porcentajePago, observacion);
            redirectAttributes.addFlashAttribute("mensajeNovedad", mensaje.getMensaje());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorNovedad", "Error al registrar la novedad.");
        }

        return "redirect:/nomina/ver?periodoId=" + idPeriodo + "&idEmpleado=" + idEmpleado;
    }

    @Override
    public String spAplicarNovedades(Integer idPeriodo, Model model) {
        return "";
    }

}
