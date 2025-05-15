package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.complex.NovedadTipoNovedadDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface INovedadService {

    MensajeDTO spAgregarNovedad(Integer idEmpleado, Integer idTipoNovedad, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion);

    MensajeDTO spAplicarNovedades(Integer idPeriodo);

    List<NovedadTipoNovedadDTO> spListarNovedadesUltimosDosMeses(Integer pnIdEmpleado);


}
