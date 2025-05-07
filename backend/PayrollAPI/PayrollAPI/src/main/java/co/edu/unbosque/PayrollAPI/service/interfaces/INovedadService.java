package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.dto.MensajeDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface INovedadService {

    MensajeDTO spAgregarNovedad(Integer idEmpleado, Integer idTipoNovedad, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion);

    MensajeDTO spAplicarNovedades(Integer idPeriodo);

}
