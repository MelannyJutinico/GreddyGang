package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.dto.regular.EmpleadoDTO;

import java.util.List;

public interface IEmpleadoService {

    EmpleadoDTO spBuscarEmpleadoPorId(Integer pnIdEmpleado);

    List<EmpleadoDTO> vwEmpleadosActivos();

}
