package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;

import java.math.BigDecimal;

public interface IDetalleNominaService {

    MensajeDTO spGenerarDecuccionesAutomaticas(Integer pnIdPeriodo);

    MensajeDTO spGenerarInteresesCesantias(Integer pnIdPeriodo);

    MensajeDTO spGenerarPrimaServicios(Integer pnIdPeriodo);

}
