package co.edu.unbosque.PayrollAPI.controller.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.ResumenConceptoDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.ResumenHoraExtraDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/concepto")
public interface IConceptoController {

    @PostMapping("/crear")
    String agregarDetalleNomina(
            @RequestParam Integer idEmpleado,
            @RequestParam Integer idPeriodo,
            @RequestParam Integer idConcepto,
            @RequestParam BigDecimal valor,
            RedirectAttributes redirect
    );

    @PostMapping("/crear-hora-extra")
    String crearHoraExtra(Integer idEmpleado,
                                 Integer idConceptoHora,
                                 Integer cantidadHoras,
                                 Integer idPeriodo,
                                 Model model,
                                 RedirectAttributes redirectAttributes);

    @GetMapping("/listar")
    @ResponseBody
    List<ResumenConceptoDTO> listarConceptos(
            @RequestParam Integer idEmpleado,
            @RequestParam Integer idPeriodo
    );

    @GetMapping("/listar-horas-extra")
    @ResponseBody
    List<ResumenHoraExtraDTO> listarHorasExtraPorEmpleadoYPeriodo(
            @RequestParam Integer idEmpleado,
            @RequestParam Integer idPeriodo
    );
}
