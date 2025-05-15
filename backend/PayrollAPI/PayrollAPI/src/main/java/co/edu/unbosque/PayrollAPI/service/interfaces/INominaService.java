package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;

import java.time.LocalDate;

public interface INominaService {

    MensajeDTO spCrearNomina(Integer pnIdEmpleado, Integer pnIdPeriodo, LocalDate pdFechaLiquidacion);

    MensajeDTO spGenerarNominaMasiva(Integer pdIdPeriodo);

    MensajeDTO spLiquidarNomina(Integer pnIdPeriodo);

}
