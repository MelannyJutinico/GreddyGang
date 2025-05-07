package co.edu.unbosque.PayrollAPI.exception.handler;

import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataBaseException.class)
    public String handleDataBaseException(DataBaseException exception, Model model){
        model.addAttribute("mensajeError", exception.getMessage());
        return "index";
    }

}
