package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.ResumenConceptoDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.ResumenHoraExtraDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IDetalleNominaRepository;
import co.edu.unbosque.PayrollAPI.repository.INominaRepositoy;
import co.edu.unbosque.PayrollAPI.service.interfaces.IDetalleNominaService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleNominaServiceImpl implements IDetalleNominaService {

    private final ModelMapper modelMapper;

    private final IDetalleNominaRepository repo;
    private final INominaRepositoy nominaRepository;

    public DetalleNominaServiceImpl(ModelMapper modelMapper, IDetalleNominaRepository repo, INominaRepositoy nominaRepository) {
        this.modelMapper = modelMapper;
        this.repo = repo;
        this.nominaRepository = nominaRepository;
    }

    @Override
    public MensajeDTO spGenerarDecuccionesAutomaticas(Integer pnIdPeriodo) {

        try {
            Mensaje mensaje = repo
                    .spGenerarDecuccionesAutomaticas(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al generar decucciones automaticas");
        }
    }

    @Override
    public MensajeDTO spGenerarInteresesCesantias(Integer pnIdPeriodo) {

        try {
            Mensaje mensaje = repo
                    .spGenerarInteresesCesantias(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al generar intereses de cesantias");
        }
    }

    @Override
    public MensajeDTO spGenerarPrimaServicios(Integer pnIdPeriodo) {

        try {
            Mensaje mensaje = repo
                    .spGenerarPrimaServicios(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al generar la prima de servicios");
        }
    }

    @Override
    @Transactional
    public MensajeDTO agregarDetalle(Integer idEmpleado, Integer idPeriodo, Integer idConcepto, BigDecimal valorTotal) {
        try {
            Integer idNomina = nominaRepository.obtenerIdNominaPorEmpleadoYPeriodo(idEmpleado, idPeriodo);
            if (idNomina == null) {
                return new MensajeDTO("ERROR", "No se encontró la nómina del empleado para este período.");
            }

            repo.spAgregarDetalleNomina(idNomina, idConcepto, valorTotal);
            return new MensajeDTO("OK", "Concepto agregado exitosamente.");

        } catch (DataAccessException e) {
            String detalle = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();
            throw new DataBaseException(detalle);
        }
    }

    @Override
    public List<ResumenConceptoDTO> listarConceptosPorEmpleadoYPeriodo(Integer idEmpleado, Integer idPeriodo) {
        List<Object[]> resultados = repo.spListarDetalleNominaEmpleado(idEmpleado, idPeriodo);

        return resultados.stream().map(obj -> {
            ResumenConceptoDTO dto = new ResumenConceptoDTO();
            dto.setTipo(obj[1].toString());
            dto.setNombre(obj[2].toString());
            dto.setValor(new BigDecimal(obj[3].toString()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ResumenHoraExtraDTO> listarHorasExtrasPorEmpleadoYPeriodo(Integer idEmpleado, Integer idPeriodo) {
        List<Object[]> resultados = repo.listarHorasExtrasPorEmpleadoYPeriodo(idEmpleado, idPeriodo);

        return resultados.stream().map(obj -> {
            ResumenHoraExtraDTO dto = new ResumenHoraExtraDTO();
            dto.setIdDetalle((Integer) obj[0]);
            dto.setIdNomina((Integer) obj[1]);
            dto.setIdPeriodo((Integer) obj[2]);
            dto.setIdEmpleado((Integer) obj[3]);
            dto.setEmpleado((String) obj[4]);
            dto.setConceptoHoraExtra((String) obj[5]);
            dto.setHoras(new BigDecimal(obj[6].toString()));
            dto.setValorHora(new BigDecimal(obj[7].toString()));
            dto.setValorTotal(new BigDecimal(obj[8].toString()));
            return dto;
        }).collect(Collectors.toList());
    }


}