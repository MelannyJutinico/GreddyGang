package co.edu.unbosque.PayrollAPI.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/provision-prestacion")
public interface IProvisionPrestacionController {

    @PostMapping("/generar-provisiones")
    String spGenerarProvisiones(@RequestParam("pnIdPeriodo") Integer pnIdPeriodo,
                                Model model);

}
