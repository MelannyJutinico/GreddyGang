package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.complex.NovedadTipoNovedadDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.INovedadRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.INovedadService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NovedadServiceImpl implements INovedadService {

    private final ModelMapper modelMapper;

    private final INovedadRepository repo;

    public NovedadServiceImpl(ModelMapper modelMapper, INovedadRepository repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }

    @Override
    public MensajeDTO spAgregarNovedad(Integer idEmpleado, Integer idTipoNovedad, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion) {

        try{
            List<Mensaje> mensajes  = repo
                    .spAgregarNovedad(idEmpleado, idTipoNovedad, fechaInicio, fechaFin, porcentajePago, observacion);

            if (!mensajes.isEmpty()) {
                return modelMapper
                        .map(mensajes.get(0),
                                MensajeDTO.class);
            }
            return new MensajeDTO("ERROR", "No se recibió respuesta del procedimiento.");
        }
        catch(DataAccessException e){
            // Extraer mensaje real si viene desde SQL Server
            String detalle = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();
            throw new DataBaseException(detalle);
        }

    }


    @Override
    public List<NovedadTipoNovedadDTO> spListarNovedadesDelPeriodo(Integer pnIdEmpleado, Integer pnIdPeriodo) {
        try{
            return repo
                    .spListarNovedadesDelPeriodo(pnIdEmpleado, pnIdPeriodo)
                    .stream()
                    .map((novedadTipoNovedadProjection -> modelMapper.map(novedadTipoNovedadProjection,NovedadTipoNovedadDTO.class)))
                    .collect(Collectors.toList());
        }
        catch(DataAccessException e){
            // Extraer mensaje real si viene desde SQL Server
            String detalle = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();
            throw new DataBaseException(detalle);
        }
    }

    @Override
    public MensajeDTO spEliminarNovedad(Integer idNovedad) {
        try {
            Mensaje mensaje = repo.spEliminarNovedad(idNovedad);
            return modelMapper.map(mensaje, MensajeDTO.class);
        } catch(DataAccessException e){
            String detalle = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();
            throw new DataBaseException(detalle);
        }
    }

}
