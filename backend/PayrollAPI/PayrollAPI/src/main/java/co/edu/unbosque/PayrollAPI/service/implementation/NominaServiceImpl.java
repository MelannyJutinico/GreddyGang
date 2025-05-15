package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.INominaRepositoy;
import co.edu.unbosque.PayrollAPI.service.interfaces.INominaService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NominaServiceImpl implements INominaService {

    private final ModelMapper modelMapper;

    private final INominaRepositoy repo;

    public NominaServiceImpl(ModelMapper modelMapper, INominaRepositoy repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }

    @Override
    public MensajeDTO spCrearNomina(Integer pnIdEmpleado, Integer pnIdPeriodo, LocalDate pdFechaLiquidacion) {

        try{
            Mensaje mensaje = repo
                    .spCrearNomina(pnIdEmpleado, pnIdPeriodo, pdFechaLiquidacion);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al crear nómina");
        }
    }

    @Override
    public MensajeDTO spGenerarNominaMasiva(Integer pdIdPeriodo) {

        try{
            Mensaje mensaje = repo
                    .spGenerarNominaMasiva(pdIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al generar las nóminas masiva");
        }
    }

    @Override
    public MensajeDTO spLiquidarNomina(Integer pnIdPeriodo) {

        try{
            Mensaje mensaje = repo
                    .spLiquidarNomina(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al liquidar la nómina");
        }
    }

}
