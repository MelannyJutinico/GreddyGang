package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IConceptoController;
import co.edu.unbosque.PayrollAPI.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.service.interfaces.IConceptoNominaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
public class ConceptoControllerImpl implements IConceptoController {

    private final IConceptoNominaService conceptoNominaService;

    public ConceptoControllerImpl(IConceptoNominaService conceptoNominaService) {
        this.conceptoNominaService = conceptoNominaService;
    }

    @Override
    public String crearConcepto(Integer idEmpleado,
                                Integer idTipoConcepto,
                                Integer idConcepto,
                                BigDecimal valor,
                                Integer idPeriodo,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        try {
            // Se asume que la nómina ya fue creada para este empleado y periodo
            // Se consulta la nómina correspondiente (aquí puedes usar un servicio que obtenga el id_nomina)
            //Integer idNomina = conceptoNominaService.obtenerIdNominaPorEmpleadoYPeriodo(idEmpleado, idPeriodo);

           // MensajeDTO mensaje = conceptoNominaService.spCrearNomina(idNomina, idConcepto, valor);
            //redirectAttributes.addFlashAttribute("mensaje", mensaje.getMensaje());

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el concepto de nómina.");
        }

        return "redirect:/nomina/ver?periodoId=" + idPeriodo + "&idEmpleado=" + idEmpleado;
    }

    @Override
    public String crearHoraExtra(Integer idEmpleado,
                                 Integer idConceptoHora,
                                 Integer cantidadHoras,
                                 Integer idPeriodo,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Se asume que la nómina ya fue creada para este empleado y periodo
            //Integer idNomina = conceptoNominaService.obtenerIdNominaPorEmpleadoYPeriodo(idEmpleado, idPeriodo);

            BigDecimal cantidadHorasDecimal = new BigDecimal(cantidadHoras);

            //MensajeDTO mensaje = conceptoNominaService.spAgregarHorasExtras(idNomina, idEmpleado, idConceptoHora, cantidadHorasDecimal);
            //redirectAttributes.addFlashAttribute("mensaje", mensaje.getMensaje());

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar horas extra.");
        }

        return "redirect:/nomina/ver?periodoId=" + idPeriodo + "&idEmpleado=" + idEmpleado;
    }
}
