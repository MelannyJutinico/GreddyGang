package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.INominaController;
import co.edu.unbosque.PayrollAPI.model.dto.complex.NovedadTipoNovedadDTO;
import co.edu.unbosque.PayrollAPI.model.entity.DesprendibleView;
import co.edu.unbosque.PayrollAPI.model.entity.NominaResumenView;
import co.edu.unbosque.PayrollAPI.service.interfaces.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    public NominaControllerImpl(IPeriodoNominaService periodoNominaService, IEmpleadoService empleadoService, ITipoNovedadService tipoNovedadService, INovedadService novedadService, ITipoConceptoService tipoConceptoService, IConceptoNominaService conceptoNominaService, INominaResumenService resumenService, IDesprendibleService service) {
        this.periodoNominaService = periodoNominaService;
        this.empleadoService = empleadoService;
        this.tipoNovedadService = tipoNovedadService;
        this.novedadService = novedadService;
        this.tipoConceptoService = tipoConceptoService;
        this.conceptoNominaService = conceptoNominaService;
        this.resumenService = resumenService;
        this.service = service;
    }


    @Override
    public String verModuloNomina(Integer periodoId, Integer idEmpleado, Model model) {
        model.addAttribute("periodo", periodoNominaService.spBuscarPeriodoPorId(periodoId));
        model.addAttribute("empleados", empleadoService.vwEmpleadosActivos());
        model.addAttribute("tipoNovedades", tipoNovedadService.vwTipoNovedadActivo());
        model.addAttribute("tipoConceptos", tipoConceptoService.listarTipoConcepto());
        model.addAttribute("conceptosDevengadosDeduccion", conceptoNominaService.vwConceptosDevengadosDeduccion());
        model.addAttribute("conceptosHorasExtra", conceptoNominaService.vwConceptosHorasExtra());

        if (idEmpleado != null) {
            List<NovedadTipoNovedadDTO> novedades = novedadService.spListarNovedadesUltimosDosMeses(idEmpleado);
            model.addAttribute("novedades", novedades);
            model.addAttribute("idEmpleadoSeleccionado", idEmpleado);
        }

        return "moduloNomina";
    }

    @PostMapping("/crear-nomina")
    public String spCrearNomina(@RequestParam("pnIdEmpleado") Integer pnIdEmpleado,
                                @RequestParam("pnIdPeriodo") Integer pnIdPeriodo,
                                @RequestParam("pdFechaLiquidacion") LocalDate pdFechaLiquidacion,
                                Model model) {
        return "";
    }

    @PostMapping("/generar-masiva")
    public String spGenerarNominaMasiva(@RequestParam("pdIdPeriodo") Integer pdIdPeriodo, Model model) {
        return "";
    }

    @PostMapping("/liquidar")
    public String spLiquidarNomina(@RequestParam("pnIdPeriodo") Integer pnIdPeriodo, Model model) {
        return "";
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
    public String mostrarResumen(String periodo, Model model) {
        List<NominaResumenView> resumen = resumenService.consultarPorPeriodo(periodo);
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

}
