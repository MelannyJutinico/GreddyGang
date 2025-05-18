package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.INovedadController;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.model.dto.complex.NovedadTipoNovedadDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.service.interfaces.INovedadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
            if ("OK".equalsIgnoreCase(mensaje.getEstado())) {
                redirectAttributes.addFlashAttribute("mensajeNovedad", mensaje.getMensaje());
            } else {
                redirectAttributes.addFlashAttribute("errorNovedad", mensaje.getMensaje());
            }
        } catch (DataBaseException e) {
            redirectAttributes.addFlashAttribute("errorNovedad", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorNovedad", "Ocurrió un error inesperado al registrar la novedad.");
        }

        return "redirect:/nomina/liquidar?periodoId=" + idPeriodo ;
    }


    @Override
    public List<NovedadTipoNovedadDTO> obtenerNovedades(
            @RequestParam("idEmpleado") Integer idEmpleado,
            @RequestParam("idPeriodo") Integer idPeriodo) {
        return novedadService.spListarNovedadesDelPeriodo(idEmpleado, idPeriodo);
    }

    @Override
    public String eliminarNovedad(Integer idNovedad, Integer idPeriodo, RedirectAttributes redirectAttributes) {
        try {
            MensajeDTO mensaje = novedadService.spEliminarNovedad(idNovedad);
            if ("OK".equalsIgnoreCase(mensaje.getEstado())) {
                redirectAttributes.addFlashAttribute("mensajeNovedad", mensaje.getMensaje());
            } else {
                redirectAttributes.addFlashAttribute("errorNovedad", mensaje.getMensaje());
            }
        } catch (DataBaseException e) {
            redirectAttributes.addFlashAttribute("errorNovedad", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorNovedad", "Ocurrió un error inesperado al registrar la novedad.");
        }

        return "redirect:/nomina/liquidar?periodoId=" + idPeriodo;
    }


}
