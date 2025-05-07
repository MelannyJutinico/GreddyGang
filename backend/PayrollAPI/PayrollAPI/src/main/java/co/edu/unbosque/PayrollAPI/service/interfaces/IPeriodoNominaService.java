package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.dto.MensajeDTO;

import java.time.LocalDate;

public interface IPeriodoNominaService {

    MensajeDTO spCrearPeriodo(LocalDate pdFechaInicio, LocalDate pdFechaFin, String vvDescripcion);

    MensajeDTO spCambiarEstadoPeriodo(Integer pnIdPeriodo, String vvNuevoEstado);

}
