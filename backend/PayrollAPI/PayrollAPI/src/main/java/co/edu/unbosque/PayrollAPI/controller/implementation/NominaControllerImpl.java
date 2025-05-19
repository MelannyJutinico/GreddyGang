package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.INominaController;
import co.edu.unbosque.PayrollAPI.model.dto.complex.NovedadTipoNovedadDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.NovedadDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PeriodoNominaDTO;
import co.edu.unbosque.PayrollAPI.model.entity.DesprendibleView;
import co.edu.unbosque.PayrollAPI.model.entity.NominaResumenView;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.model.entity.PeriodoNomina;
import co.edu.unbosque.PayrollAPI.service.interfaces.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class NominaControllerImpl implements INominaController {
 
    private final IPeriodoNominaService periodoNominaService;
    private final IEmpleadoService empleadoService;
    private final ITipoNovedadService tipoNovedadService;
    private final INovedadService novedadService;
    private final ITipoConceptoService tipoConceptoService;
    private final IConceptoNominaService conceptoNominaService;
    private final INominaResumenService resumenService;
    private final IDesprendibleService service;
    private final INominaService nominaService;

    public NominaControllerImpl(IPeriodoNominaService periodoNominaService, IEmpleadoService empleadoService, ITipoNovedadService tipoNovedadService, INovedadService novedadService, ITipoConceptoService tipoConceptoService, IConceptoNominaService conceptoNominaService, INominaService nominaService, IPeriodoNominaService periodoNominaService1, IEmpleadoService empleadoService1, ITipoNovedadService tipoNovedadService1, INovedadService novedadService1, ITipoConceptoService tipoConceptoService1, IConceptoNominaService conceptoNominaService1, INominaResumenService resumenService, IDesprendibleService service, INominaService nominaService1) {
        this.periodoNominaService = periodoNominaService1;
        this.empleadoService = empleadoService1;
        this.tipoNovedadService = tipoNovedadService1;
        this.novedadService = novedadService1;
        this.tipoConceptoService = tipoConceptoService1;
        this.conceptoNominaService = conceptoNominaService1;
        this.resumenService = resumenService;
        this.service = service;
        this.nominaService = nominaService1;
    }


    @Override
    public String verModuloLiquidacionNomina(Integer periodoId, Integer idEmpleado, Model model, HttpSession session) {
        PeriodoNominaDTO periodo = periodoNominaService.spBuscarPeriodoPorId(periodoId);
        model.addAttribute("periodo", periodo);
        session.setAttribute("periodoId", periodo.getIdPeriodo());
        session.setAttribute("periodoNombre", periodo.getDescripcion());
        model.addAttribute("empleados", empleadoService.vwEmpleadosActivos());
        model.addAttribute("tipoNovedades", tipoNovedadService.vwTipoNovedadActivo());
        model.addAttribute("tipoConceptos", tipoConceptoService.listarTipoConcepto());
        model.addAttribute("conceptosDevengadosDeduccion", conceptoNominaService.vwConceptosDevengadosDeduccion());
        model.addAttribute("conceptosHorasExtra", conceptoNominaService.vwConceptosHorasExtra());

        try {
            String mensaje = nominaService.spGenerarNominaMasiva(periodoId);
            model.addAttribute("mensaje", mensaje);
        } catch (DataBaseException e) {
            model.addAttribute("error", e.getMessage());

        }


        if (idEmpleado != null) {
            List<NovedadTipoNovedadDTO> novedades = novedadService.spListarNovedadesDelPeriodo(idEmpleado, periodoId);
            model.addAttribute("novedades", novedades);
            model.addAttribute("idEmpleadoSeleccionado", idEmpleado); // <-- importante
        }


        return "moduloNomina";
    }



    @Override
    public List<DesprendibleView> getByNomina(Long idNomina) {

        return service.getByNominaId(idNomina);
    }

    @Override
    public List<DesprendibleView> getByEmpleadoAndPeriodo(Long idEmpleado, Long idPeriodo) {
        return service.getByEmpleadoAndPeriodo(idEmpleado, idPeriodo);
    }

    @Override
    public String mostrarResumen(Integer idPeriodo, Model model, HttpSession session)  {
        PeriodoNominaDTO periodo = periodoNominaService.spBuscarPeriodoPorId(idPeriodo);
        model.addAttribute("periodo", periodo);
        session.setAttribute("periodoId", periodo.getIdPeriodo());
        session.setAttribute("periodoNombre", periodo.getDescripcion());
        List<NominaResumenView> resumen = resumenService.consultarPorPeriodo(String.valueOf(idPeriodo));
        model.addAttribute("resumen", resumen);
        return "resumenNomina";
    }

    @Override
    public String vistaDesprendible(@RequestParam("idEmpleado") Long idEmpleado,
                                    @RequestParam("idPeriodo") Long idPeriodo,
                                    Model model) {
        List<DesprendibleView> desprendible = service.getByEmpleadoAndPeriodo(idEmpleado, idPeriodo);
        model.addAttribute("desprendible", desprendible);
        return "desprendible";
    }

    @Override
    public String liquidarPeriodo(
            @RequestParam("idPeriodo") Integer idPeriodo,
            RedirectAttributes redirectAttributes
    ) {
        try {
            MensajeDTO mensaje = nominaService.liquidarPeriodo(idPeriodo);

            if ("OK".equalsIgnoreCase(mensaje.getEstado())) {
                redirectAttributes.addFlashAttribute("mensaje", mensaje.getMensaje());
            } else {
                redirectAttributes.addFlashAttribute("error", mensaje.getMensaje());
            }

        } catch (DataBaseException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado al liquidar el período.");
        }

        return "redirect:/periodo-nomina/cerrados"; // <-- redirección final
    }


}
