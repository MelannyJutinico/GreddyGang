package co.edu.unbosque.PayrollAPI.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@RequestMapping("/periodo-nomina")
public interface IPeriodoNominaController {

    @PostMapping("/crear-periodo")
    String spCrearPeriodo(
            @RequestParam("mesSeleccionado") String mesSeleccionado,
            @RequestParam("vvDescripcion") String vvDescripcion,
            Model model,
            RedirectAttributes redirectAttributes);

    @PostMapping("/cambiar-estado-periodo")
    String spCambiarEstadoPeriodo(@RequestParam("pnIdPeriodo") Integer pnIdPeriodo,
                                  @RequestParam("vvNuevoEstado") String vvNuevoEstado,
                                  Model model);

    @GetMapping("/activo")
    String mostrarPeriodosActivos(Model model);

    @GetMapping("/cerrados")
    public String listarPeriodosCerrados(Model model, @ModelAttribute("mensaje") String mensaje, @ModelAttribute("error") String error
    );

}
