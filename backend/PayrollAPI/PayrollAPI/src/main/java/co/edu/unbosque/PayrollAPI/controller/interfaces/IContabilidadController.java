package co.edu.unbosque.PayrollAPI.controller.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.AportePatronalPeriodoView;
import co.edu.unbosque.PayrollAPI.model.entity.ProvisionPrestacionPeriodoView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/contabilidad")
public interface IContabilidadController {

    @GetMapping("/ver")
    String verContabilidad(@RequestParam("idPeriodo") Integer idPeriodo, Model model);

    @GetMapping("/detalle-aportes")
    @ResponseBody
    List<AportePatronalPeriodoView> getDetalleAportes(
            @RequestParam("idPeriodo") Integer idPeriodo,
            @RequestParam("idEmpleado") Integer idEmpleado);

    @GetMapping("/detalle-provisiones")
    @ResponseBody
    List<ProvisionPrestacionPeriodoView> getDetalleProvisiones(
            @RequestParam("idPeriodo") Integer idPeriodo,
            @RequestParam("idEmpleado") Integer idEmpleado);
}
