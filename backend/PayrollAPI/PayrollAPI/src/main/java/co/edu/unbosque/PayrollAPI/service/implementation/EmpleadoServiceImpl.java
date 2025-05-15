package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.EmpleadoDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Empleado;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IEmpleadoRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IEmpleadoService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final IEmpleadoRepository repo;
    private final ModelMapper modelMapper;

    public EmpleadoServiceImpl(IEmpleadoRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmpleadoDTO spBuscarEmpleadoPorId(Integer pnIdEmpleado) {

        try{
            Empleado empleado = repo.spBuscarEmpleadoPorId(pnIdEmpleado);
            return modelMapper
                    .map(empleado, EmpleadoDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al buscar empleado");
        }

    }

    @Override
    @Transactional
    public void inactivarEmpleado(Integer idEmpleado) {
        try {
            repo.spInactivarEmpleado(idEmpleado);
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al inactivar el empleado.");
        }
    }


    @Override
    public List<EmpleadoDTO> vwEmpleadosActivos() {
        try{
            return repo
                    .vwEmpleadosActivos()
                    .stream()
                    .map((empleado -> modelMapper.map(empleado, EmpleadoDTO.class)))
                    .collect(Collectors.toList());
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al listar todos los empleados");
        }
    }

    @Override
    public List<EmpleadoDTO> listarPorEstado(boolean activo) {
        try {
            List<Empleado> empleados;
            if(activo) {
                empleados = repo.vwEmpleadosActivos();
            }else {
                empleados = repo.vwEmpleadosInactivos();
            }
                return empleados
                        .stream()
                        .map((empleado -> modelMapper.map(empleado, EmpleadoDTO.class)))
                        .collect(Collectors.toList());
            }
            catch(DataAccessException e){
                throw new DataBaseException("Error al listar todos los empleados");
            }
    }

    @Override
    public List<EmpleadoDTO> vwEmpleados() {
        try{
            return repo
                    .vwEmpleados()
                    .stream()
                    .map((empleado -> modelMapper.map(empleado, EmpleadoDTO.class)))
                    .collect(Collectors.toList());
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al listar todos los empleados");
        }
    }

    @Override
    public void crearEmpleadoDesdeDTO(EmpleadoDTO dto) {
        repo.crearEmpleado(
                dto.getNombre(),
                dto.getCargo(),
                dto.getDepartamento(),
                dto.getSalarioBase(),
                dto.getFechaIngreso(),
                dto.getNivelRiesgoDTO().getIdNivel(),
                dto.getTipoContratoDTO().getIdTipoContrato()
        );
    }
}
