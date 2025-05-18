package co.edu.unbosque.PayrollAPI.controller.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.complex.NovedadTipoNovedadDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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


    @GetMapping("/listar")
    @ResponseBody
    List<NovedadTipoNovedadDTO> obtenerNovedades(
            @RequestParam("idEmpleado") Integer idEmpleado,
            @RequestParam("idPeriodo") Integer idPeriodo);

    @PostMapping("/eliminar")
    String eliminarNovedad(@RequestParam("idNovedad") Integer idNovedad,
                                  @RequestParam("idPeriodo") Integer idPeriodo,
                                  RedirectAttributes redirectAttributes);

}
