package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.dto.regular.EmpleadoDTO;

import java.util.List;

public interface IEmpleadoService {

    EmpleadoDTO spBuscarEmpleadoPorId(Integer pnIdEmpleado);

    List<EmpleadoDTO> vwEmpleadosActivos();
    void inactivarEmpleado(Integer idEmpleado);
    List<EmpleadoDTO> listarPorEstado(boolean activo);
    List<EmpleadoDTO> vwEmpleados();
    void crearEmpleadoDesdeDTO(EmpleadoDTO dto);

}
