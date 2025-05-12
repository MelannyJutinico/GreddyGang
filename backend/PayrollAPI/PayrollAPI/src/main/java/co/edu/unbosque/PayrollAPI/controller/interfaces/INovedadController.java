package co.edu.unbosque.PayrollAPI.controller.interfaces;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequestMapping("/novedad")
public interface INovedadController {

    @PostMapping("/crear")
    String spAgregarNovedad(
            @RequestParam("idEmpleado") Integer idEmpleado,
            @RequestParam("idTipoNovedad") Integer idTipoNovedad,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam("porcentajePago") BigDecimal porcentajePago,
            @RequestParam("observacion") String observacion,
            @RequestParam("idPeriodo") Integer idPeriodo,
            Model model,
            RedirectAttributes redirectAttributes);

    @PostMapping("/aplicar-novedades")
    String spAplicarNovedades(@RequestParam("idPeriodo") Integer idPeriodo,
                              Model model);

}
