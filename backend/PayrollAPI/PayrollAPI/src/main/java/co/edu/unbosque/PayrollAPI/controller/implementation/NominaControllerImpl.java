package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.INominaController;
import org.springframework.ui.Model;

import java.time.LocalDate;

public class NominaControllerImpl implements INominaController {

    @Override
    public String spCrearNomina(Integer pnIdEmpleado, Integer pnIdPeriodo, LocalDate pdFechaLiquidacion, Model model) {
        return "";
    }

    @Override
    public String spGenerarNominaMasiva(Integer pdIdPeriodo, Model model) {
        return "";
    }

    @Override
    public String spLiquidarNomina(Integer pnIdPeriodo, Model model) {
        return "";
    }

}
