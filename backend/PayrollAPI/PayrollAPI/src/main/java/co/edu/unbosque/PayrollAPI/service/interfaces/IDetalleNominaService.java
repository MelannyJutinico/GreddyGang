package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.ResumenConceptoDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.ResumenHoraExtraDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;

import java.math.BigDecimal;
import java.util.List;

public interface IDetalleNominaService {

    MensajeDTO spGenerarDecuccionesAutomaticas(Integer pnIdPeriodo);

    MensajeDTO spGenerarInteresesCesantias(Integer pnIdPeriodo);

    MensajeDTO spGenerarPrimaServicios(Integer pnIdPeriodo);

    MensajeDTO agregarDetalle(Integer idEmpleado, Integer idPeriodo, Integer idConcepto, BigDecimal valorTotal);

    List<ResumenConceptoDTO> listarConceptosPorEmpleadoYPeriodo(Integer idEmpleado, Integer idPeriodo);

    List<ResumenHoraExtraDTO> listarHorasExtrasPorEmpleadoYPeriodo(Integer idEmpleado, Integer idPeriodo);
}
