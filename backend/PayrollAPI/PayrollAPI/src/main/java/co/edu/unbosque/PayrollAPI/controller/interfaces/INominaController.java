package co.edu.unbosque.PayrollAPI.controller.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.DesprendibleView;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.List;

@RequestMapping("/nomina")
public interface INominaController {

    @GetMapping("/liquidar")
    String verModuloLiquidacionNomina(@RequestParam("periodoId") Integer periodoId,
                           @RequestParam(value = "idEmpleado", required = false) Integer idEmpleado,
                           Model model, HttpSession session);


    @GetMapping("{idNomina}/desprendible")
    List<DesprendibleView> getByNomina(@PathVariable Long idNomina);

    @GetMapping("/vista-desprendible")
    String vistaDesprendible(@RequestParam("idEmpleado") Long idEmpleado,
                                    @RequestParam("idPeriodo") Long idPeriodo,
                                    Model model);

    @GetMapping("/empleado/{idEmpleado}/periodo/{idPeriodo}/desprendible")
     List<DesprendibleView> getByEmpleadoAndPeriodo(
            @PathVariable Long idEmpleado,
            @PathVariable Long idPeriodo
    );

    @GetMapping("/resumen")
    String mostrarResumen(
            @RequestParam("periodo") Integer periodo,
            Model model, HttpSession session
    );

    @PostMapping("/liquidar")
    String liquidarPeriodo(
            @RequestParam("idPeriodo") Integer idPeriodo,
            RedirectAttributes redirectAttributes
    );



}
