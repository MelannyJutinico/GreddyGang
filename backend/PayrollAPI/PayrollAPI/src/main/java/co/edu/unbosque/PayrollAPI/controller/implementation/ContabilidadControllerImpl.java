package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IContabilidadController;
import co.edu.unbosque.PayrollAPI.model.entity.AportePatronalPeriodoView;
import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadEmpleadoView;
import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadTotalesPeriodoView;
import co.edu.unbosque.PayrollAPI.model.entity.ProvisionPrestacionPeriodoView;
import co.edu.unbosque.PayrollAPI.repository.IAportePatronalViewRepository;
import co.edu.unbosque.PayrollAPI.repository.IProvisionesViewRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IContabilidadEmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ContabilidadControllerImpl implements IContabilidadController {

   private final IContabilidadEmpleadoService contabilidadService;
   private final IAportePatronalViewRepository aportePatronalService;
   private final IProvisionesViewRepository provisionPrestacionService;


    public ContabilidadControllerImpl(IContabilidadEmpleadoService contabilidadService, IAportePatronalViewRepository aportePatronalService, IProvisionesViewRepository provisionPrestacionService) {

        this.contabilidadService = contabilidadService;

        this.aportePatronalService = aportePatronalService;
        this.provisionPrestacionService = provisionPrestacionService;
    }

    @Override
    public String verContabilidad(@RequestParam("idPeriodo") Integer idPeriodo, Model model) {
        List<ContabilidadEmpleadoView> contabilidad = contabilidadService.consultarPorPeriodo(idPeriodo);
        model.addAttribute("contabilidad", contabilidad);
        ContabilidadTotalesPeriodoView totales = contabilidadService.getTotalesByPeriodo(idPeriodo);
        model.addAttribute("totales", totales);

        return "vistaContabilidad";
    }


    @Override
    public List<AportePatronalPeriodoView> getDetalleAportes(
            @RequestParam("idPeriodo") Integer idPeriodo,
            @RequestParam("idEmpleado") Integer idEmpleado) {
        return aportePatronalService.findAportesByPeriodoAndEmpleado(idPeriodo, idEmpleado);
    }

    @Override
    public List<ProvisionPrestacionPeriodoView> getDetalleProvisiones(
            @RequestParam("idPeriodo") Integer idPeriodo,
            @RequestParam("idEmpleado") Integer idEmpleado) {
        return provisionPrestacionService.findProvisionesByPeriodoAndEmpleado(idPeriodo, idEmpleado);
    }



}
