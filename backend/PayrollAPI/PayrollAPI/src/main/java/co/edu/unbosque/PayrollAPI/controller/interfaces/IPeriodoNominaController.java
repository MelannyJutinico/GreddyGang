package co.edu.unbosque.PayrollAPI.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@RequestMapping("/periodo-nomina")
public interface IPeriodoNominaController {

    @PostMapping("/crear-periodo")
    String spCrearPeriodo(@RequestParam("pdFechaInicio") LocalDate pdFechaInicio,
                          @RequestParam("pdFechaFin") LocalDate pdFechaFin,
                          @RequestParam("vvDescripcion") String vvDescripcion,
                          Model model);

    @PostMapping("/cambiar-estado-periodo")
    String spCambiarEstadoPeriodo(@RequestParam("pnIdPeriodo") Integer pnIdPeriodo,
                                  @RequestParam("vvNuevoEstado") String vvNuevoEstado,
                                  Model model);

}
