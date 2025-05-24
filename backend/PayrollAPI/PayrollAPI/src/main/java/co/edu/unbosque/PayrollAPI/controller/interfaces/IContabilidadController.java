package co.edu.unbosque.PayrollAPI.controller.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.AportePatronalPeriodoView;
import co.edu.unbosque.PayrollAPI.model.entity.ProvisionPrestacionPeriodoView;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@RequestMapping("/contabilidad")
public interface IContabilidadController {

    @GetMapping("/ver")
    String verContabilidad(@RequestParam("idPeriodo") Integer idPeriodo, Model model, HttpSession session) ;

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

    @GetMapping("/aportes")
    String verAportes(@RequestParam(required = false) Integer idPeriodo, Model model,  HttpSession session);

    @GetMapping("/aportes/excel")
    void exportarAportesExcel(@RequestParam("idPeriodo") Integer idPeriodo, HttpServletResponse response) throws IOException;

    @GetMapping("/provisiones")
    String verProvisiones(@RequestParam(required = false) Integer idPeriodo, Model model, HttpSession session);

    @GetMapping("/provisiones/excel")
    void exportarProvisionesExcel(@RequestParam("idPeriodo") Integer idPeriodo, HttpServletResponse response);

}
