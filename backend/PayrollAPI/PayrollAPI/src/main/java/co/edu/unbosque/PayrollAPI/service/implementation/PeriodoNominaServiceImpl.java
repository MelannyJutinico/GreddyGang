package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.dto.MensajeDTO;
import co.edu.unbosque.PayrollAPI.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IPeriodoNominaRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IPeriodoNominaService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PeriodoNominaServiceImpl implements IPeriodoNominaService {

    private final ModelMapper modelMapper;

    private final IPeriodoNominaRepository repo;

    public PeriodoNominaServiceImpl(ModelMapper modelMapper, IPeriodoNominaRepository repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }

    @Override
    public MensajeDTO spCrearPeriodo(LocalDate pdFechaInicio, LocalDate pdFechaFin, String vvDescripcion) {

        try{
            Mensaje mensaje = repo
                    .spCrearPeriodo(pdFechaInicio, pdFechaFin, vvDescripcion);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al crear periodo");
        }
    }

    @Override
    public MensajeDTO spCambiarEstadoPeriodo(Integer pnIdPeriodo, String vvNuevoEstado) {

        try{
            Mensaje mensaje = repo
                    .spCambiarEstadoPeriodo(pnIdPeriodo, vvNuevoEstado);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al cambiar el estado del periodo");
        }
    }

}
