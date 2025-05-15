package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IEmpleadoController;
import co.edu.unbosque.PayrollAPI.model.dto.regular.EmpleadoDTO;
import co.edu.unbosque.PayrollAPI.service.interfaces.IEmpleadoService;
import co.edu.unbosque.PayrollAPI.service.interfaces.INivelRiesgoService;
import co.edu.unbosque.PayrollAPI.service.interfaces.ITipoContratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmpleadoControllerImpl implements IEmpleadoController {

    private final IEmpleadoService service;
    private final ITipoContratoService tipoContratoService;
    private final INivelRiesgoService nivelRiesgoService;

    public EmpleadoControllerImpl(IEmpleadoService service, ITipoContratoService tipoContratoService, INivelRiesgoService nivelRiesgoService) {
        this.service = service;
        this.tipoContratoService = tipoContratoService;
        this.nivelRiesgoService = nivelRiesgoService;
    }

    @Override
    public String crearEmpleado(@ModelAttribute("empleado") EmpleadoDTO dto, RedirectAttributes redirectAttributes) {
        service.crearEmpleadoDesdeDTO(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Empleado creado exitosamente.");
        return "redirect:/empleados/lista";
    }
    @Override
    public String mostrarFormularioEmpleado(Model model) {
        model.addAttribute("empleado", new EmpleadoDTO());
        model.addAttribute("tiposContrato", tipoContratoService.obtenerTodos());
        model.addAttribute("nivelesRiesgo", nivelRiesgoService.obtenerTodos());
        return "registrarEmpleado";
    }

    @Override
    public String desactivarEmpleado(@RequestParam("idEmpleado") Integer idEmpleado, RedirectAttributes redirectAttributes) {
        service.inactivarEmpleado(idEmpleado);
        redirectAttributes.addFlashAttribute("successMessage", "Empleado desactivado exitosamente.");
        return "redirect:/empleados/lista";
    }


    @Override
    public String mostrarListaEmpleados(@RequestParam(value = "estado", required = false, defaultValue = "todos") String estado,
                                        Model model) {
        List<EmpleadoDTO> empleados;
        if (estado.equals("activos")) {
            empleados = service.listarPorEstado(true);
        } else if (estado.equals("inactivos")) {
            empleados = service.listarPorEstado(false);
        } else {
            empleados = service.vwEmpleados();
        }
        model.addAttribute("empleados", empleados);
        model.addAttribute("estado", estado);
        return "listaEmpleados";
    }



}
