package co.edu.unbosque.PayrollAPI.controller.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.EmpleadoDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/empleados")
public interface IEmpleadoController {

    @PostMapping("/crear")
    String crearEmpleado(@ModelAttribute("empleado") EmpleadoDTO dto, RedirectAttributes redirectAttributes);

    @GetMapping("/crear")
    String mostrarFormularioEmpleado(Model model);

    @GetMapping("/lista")
    String mostrarListaEmpleados(@RequestParam(value = "estado", required = false, defaultValue = "todos") String estado,
                                        Model model);

    @PostMapping("/desactivar")
    String desactivarEmpleado(@RequestParam("idEmpleado") Integer idEmpleado, RedirectAttributes redirectAttributes);
}
