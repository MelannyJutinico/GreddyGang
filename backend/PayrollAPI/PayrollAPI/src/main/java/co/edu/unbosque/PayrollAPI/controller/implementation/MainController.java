package co.edu.unbosque.PayrollAPI.controller.implementation;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mostrarInicio() {
        return "index";
    }
}
