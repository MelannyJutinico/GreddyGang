package co.edu.unbosque.PayrollAPI.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@RequestMapping("/detalle-nomina")
public interface IDetalleNominaController {

    @PostMapping("/generar-deducciones")
    String spGenerarDecuccionesAutomaticas(@RequestParam("pnIdPeriodo") Integer pnIdPeriodo,
                                           Model model);

    @PostMapping("/generar-intereses-cesantias")
    String spGenerarInteresesCesantias(@RequestParam("pnIdPeriodo") Integer pnIdPeriodo,
                                       Model model);

    @PostMapping("/generar-prima")
    String spGenerarPrimaServicios(@RequestParam("pnIdPeriodo") Integer pnIdPeriodo,
                                   Model model);

    @PostMapping("/crear-nomina")
    String spCrearNomina(@RequestParam("pnIdNomina") Integer pnIdNomina,
                         @RequestParam("pnIdConcepto") Integer pnIdConcepto,
                         @RequestParam("pnValorTotal") BigDecimal pnValorTotal, Model model);

    @PostMapping("/agregar-horas-extras")
    String spAgregarHorasExtras(@RequestParam("pnIdNomina") Integer pnIdNomina,
                                @RequestParam("pdIdEmpleado") Integer pdIdEmpleado,
                                @RequestParam("pnIdConcepto") Integer pnIdConcepto,
                                @RequestParam("vnCantidadHoras") BigDecimal vnCantidadHoras, Model model);

}
