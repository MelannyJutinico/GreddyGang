package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IDetalleNominaController;
import org.springframework.ui.Model;

import java.math.BigDecimal;

public class DetalleNominaControllerImpl implements IDetalleNominaController {

    @Override
    public String spGenerarDecuccionesAutomaticas(Integer pnIdPeriodo, Model model) {
        return "";
    }

    @Override
    public String spGenerarInteresesCesantias(Integer pnIdPeriodo, Model model) {
        return "";
    }

    @Override
    public String spGenerarPrimaServicios(Integer pnIdPeriodo, Model model) {
        return "";
    }

    @Override
    public String spCrearNomina(Integer pnIdNomina, Integer pnIdConcepto, BigDecimal pnValorTotal, Model model) {
        return "";
    }

    @Override
    public String spAgregarHorasExtras(Integer pnIdNomina, Integer pdIdEmpleado, Integer pnIdConcepto, BigDecimal vnCantidadHoras, Model model) {
        return "";
    }

}
