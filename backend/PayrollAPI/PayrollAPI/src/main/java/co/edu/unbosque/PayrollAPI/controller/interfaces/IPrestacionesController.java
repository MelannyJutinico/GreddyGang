package co.edu.unbosque.PayrollAPI.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/prestaciones")
public interface IPrestacionesController {

    @GetMapping
     String verModuloPrestaciones(
            @RequestParam(required = false) Integer idPeriodo,
            Model model,
            @ModelAttribute("mensaje") String mensaje,
            @ModelAttribute("estado") String estado
    );

    @PostMapping("/generar")
    String generarPrestacion(@RequestParam Integer idPeriodo, @RequestParam String accion, RedirectAttributes redirect);


}
