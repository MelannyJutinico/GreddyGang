package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IDetalleNominaRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IDetalleNominaService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DetalleNominaServiceImpl implements IDetalleNominaService {

    private final ModelMapper modelMapper;

    private final IDetalleNominaRepository repo;

    public DetalleNominaServiceImpl(ModelMapper modelMapper, IDetalleNominaRepository repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }

    @Override
    public MensajeDTO spGenerarDecuccionesAutomaticas(Integer pnIdPeriodo) {

        try{
            Mensaje mensaje = repo
                    .spGenerarDecuccionesAutomaticas(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al generar decucciones automaticas");
        }
    }

    @Override
    public MensajeDTO spGenerarInteresesCesantias(Integer pnIdPeriodo) {

        try{
            Mensaje mensaje = repo
                    .spGenerarInteresesCesantias(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al generar intereses de cesantias");
        }
    }

    @Override
    public MensajeDTO spGenerarPrimaServicios(Integer pnIdPeriodo) {

        try{
            Mensaje mensaje = repo
                    .spGenerarPrimaServicios(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al generar la prima de servicios");
        }
    }

    @Override
    public MensajeDTO spCrearNomina(Integer pnIdNomina, Integer pnIdConcepto, BigDecimal pnValorTotal) {

        try{
            Mensaje mensaje = repo
                    .spCrearNomina(pnIdNomina, pnIdConcepto, pnValorTotal);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al crear la nómina");
        }
    }

    @Override
    public MensajeDTO spAgregarHorasExtras(Integer pnIdNomina, Integer pdIdEmpleado, Integer pnIdConcepto, BigDecimal vnCantidadHoras) {

        try{
            Mensaje mensaje = repo
                    .spAgregarHorasExtras(pnIdNomina, pdIdEmpleado, pnIdConcepto, vnCantidadHoras);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al agregar horas extras");
        }
    }

}
