package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.INovedadController;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NovedadControllerImpl implements INovedadController {

    @Override
    public String spAgregarNovedad(Integer idEmpleado, Integer idTipoNovedad, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion, Model model) {
        return "";
    }

    @Override
    public String spAplicarNovedades(Integer idPeriodo, Model model) {
        return "";
    }

}
