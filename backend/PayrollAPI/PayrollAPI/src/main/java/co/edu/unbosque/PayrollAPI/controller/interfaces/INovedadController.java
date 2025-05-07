package co.edu.unbosque.PayrollAPI.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequestMapping("/novedad")
public interface INovedadController {

    @PostMapping("/agregar-novedad")
    String spAgregarNovedad(@RequestParam("idEmpleado") Integer idEmpleado,
                            @RequestParam("idTipoNovedad") Integer idTipoNovedad,
                            @RequestParam("fechaInicio") LocalDate fechaInicio,
                            @RequestParam("fechaFin") LocalDate fechaFin,
                            @RequestParam("porcentajePago") BigDecimal porcentajePago,
                            @RequestParam("observacion") String observacion,
                                Model model);

    @PostMapping("/aplicar-novedades")
    String spAplicarNovedades(@RequestParam("idPeriodo") Integer idPeriodo,
                              Model model);

}
