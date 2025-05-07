package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IPeriodoNominaController;
import org.springframework.ui.Model;

import java.time.LocalDate;

public class PeriodoNominaControllerImpl implements IPeriodoNominaController {

    @Override
    public String spCrearPeriodo(LocalDate pdFechaInicio, LocalDate pdFechaFin, String vvDescripcion, Model model) {
        return "";
    }

    @Override
    public String spCambiarEstadoPeriodo(Integer pnIdPeriodo, String vvNuevoEstado, Model model) {
        return "";
    }

}
