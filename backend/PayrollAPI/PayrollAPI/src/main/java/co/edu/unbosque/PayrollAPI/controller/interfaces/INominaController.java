package co.edu.unbosque.PayrollAPI.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@RequestMapping("/nomina")
public interface INominaController {

    @PostMapping("/crear-nomina")
    String spCrearNomina(@RequestParam("pnIdEmpleado") Integer pnIdEmpleado,
                         @RequestParam("pnIdPeriodo") Integer pnIdPeriodo,
                         @RequestParam("pdFechaLiquidacion") LocalDate pdFechaLiquidacion,
                         Model model);

    @PostMapping("/generar-nomina-masiva")
    String spGenerarNominaMasiva(@RequestParam("pdIdPeriodo") Integer pdIdPeriodo,
                                 Model model);

    @PostMapping("/liquidar-nomina")
    String spLiquidarNomina(@RequestParam("pdIdPeriodo") Integer pnIdPeriodo,
                            Model model);

}
