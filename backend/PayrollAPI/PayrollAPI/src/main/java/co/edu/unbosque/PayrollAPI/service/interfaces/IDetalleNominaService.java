package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.dto.MensajeDTO;

import java.math.BigDecimal;

public interface IDetalleNominaService {

    MensajeDTO spGenerarDecuccionesAutomaticas(Integer pnIdPeriodo);

    MensajeDTO spGenerarInteresesCesantias(Integer pnIdPeriodo);

    MensajeDTO spGenerarPrimaServicios(Integer pnIdPeriodo);

    MensajeDTO spCrearNomina(Integer pnIdNomina, Integer pnIdConcepto, BigDecimal pnValorTotal);

    MensajeDTO spAgregarHorasExtras(Integer pnIdNomina, Integer pdIdEmpleado, Integer pnIdConcepto, BigDecimal vnCantidadHoras);

}
