package co.edu.unbosque.PayrollAPI.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@RequestMapping("/conceptos")
public interface IConceptoController {

    @PostMapping("/crear-concepto")
    String crearConcepto(Integer idEmpleado,
                         Integer idTipoConcepto,
                         Integer idConcepto,
                         BigDecimal valor,
                         Integer idPeriodo,
                         Model model,
                         RedirectAttributes redirectAttributes);

    @PostMapping("/crear-hora-extra")
    String crearHoraExtra(Integer idEmpleado,
                                 Integer idConceptoHora,
                                 Integer cantidadHoras,
                                 Integer idPeriodo,
                                 Model model,
                                 RedirectAttributes redirectAttributes);

}
