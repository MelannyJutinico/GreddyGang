package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IConceptoController;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.ResumenConceptoDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.ResumenHoraExtraDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.service.interfaces.IConceptoNominaService;
import co.edu.unbosque.PayrollAPI.service.interfaces.IDetalleNominaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ConceptoControllerImpl implements IConceptoController {

    private final IDetalleNominaService detalleNominaService;
    private final IConceptoNominaService conceptoNominaService;

    public ConceptoControllerImpl(IConceptoNominaService conceptoNominaService, IDetalleNominaService detalleNominaService, IConceptoNominaService conceptoNominaService1) {

        this.detalleNominaService = detalleNominaService;
        this.conceptoNominaService = conceptoNominaService1;
    }

    @Override
    public String agregarDetalleNomina(
            @RequestParam Integer idEmpleado,
            @RequestParam Integer idPeriodo,
            @RequestParam Integer idConcepto,
            @RequestParam BigDecimal valor,
            RedirectAttributes redirectAttributes
    ) {
        try {
            MensajeDTO mensaje = detalleNominaService.agregarDetalle(idEmpleado, idPeriodo, idConcepto, valor);

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
    public String crearHoraExtra(Integer idEmpleado,
                                 Integer idConceptoHora,
                                 Integer cantidadHoras,
                                 Integer idPeriodo,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        try {
            MensajeDTO mensaje = conceptoNominaService.spAgregarHorasExtras(idPeriodo, idEmpleado, idConceptoHora, BigDecimal.valueOf(cantidadHoras));

            if ("OK".equalsIgnoreCase(mensaje.getEstado())) {
                redirectAttributes.addFlashAttribute("mensajeNovedad", mensaje.getMensaje());
            } else {
                redirectAttributes.addFlashAttribute("errorNovedad", mensaje.getMensaje());
            }

        } catch (DataBaseException e) {
            redirectAttributes.addFlashAttribute("errorNovedad", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorNovedad", "Ocurrió un error inesperado al registrar las horas extras.");
        }

        return "redirect:/nomina/liquidar?periodoId=" + idPeriodo ;
    }

    @Override
    public List<ResumenConceptoDTO> listarConceptos(
            @RequestParam Integer idEmpleado,
            @RequestParam Integer idPeriodo
    ) {
        return detalleNominaService.listarConceptosPorEmpleadoYPeriodo(idEmpleado, idPeriodo);
    }

    @Override
    public List<ResumenHoraExtraDTO> listarHorasExtraPorEmpleadoYPeriodo(
            @RequestParam Integer idEmpleado,
            @RequestParam Integer idPeriodo
    ) {
        return detalleNominaService.listarHorasExtrasPorEmpleadoYPeriodo(idEmpleado, idPeriodo);
    }

}
