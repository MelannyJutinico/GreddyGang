package co.edu.unbosque.PayrollAPI.controller.interfaces;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auditoria")
public interface IAuditoriaController {

    String verAuditoria(Model model, HttpSession session);
}
