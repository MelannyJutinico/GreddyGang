package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.dto.MensajeDTO;
import co.edu.unbosque.PayrollAPI.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.INovedadRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.INovedadService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

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
            Mensaje mensaje = repo
                    .spAgregarNovedad(idEmpleado, idTipoNovedad, fechaInicio, fechaFin, porcentajePago, observacion);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al agregar novedad");
        }
    }

    @Override
    public MensajeDTO spAplicarNovedades(Integer idPeriodo) {

        try{
            Mensaje mensaje = repo
                    .spAplicarNovedades(idPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al aplicar novedades");
        }
    }

}
