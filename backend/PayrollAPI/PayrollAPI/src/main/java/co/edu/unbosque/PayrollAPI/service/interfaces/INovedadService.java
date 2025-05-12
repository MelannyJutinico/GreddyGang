package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.dto.complex.NovedadTipoNovedadDTO;
import co.edu.unbosque.PayrollAPI.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.projection.NovedadTipoNovedadProjection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface INovedadService {

    MensajeDTO spAgregarNovedad(Integer idEmpleado, Integer idTipoNovedad, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion);

    MensajeDTO spAplicarNovedades(Integer idPeriodo);

    List<NovedadTipoNovedadDTO> spListarNovedadesUltimosDosMeses(Integer pnIdEmpleado);


}
