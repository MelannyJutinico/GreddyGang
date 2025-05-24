package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.INominaRepositoy;
import co.edu.unbosque.PayrollAPI.service.interfaces.INominaService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NominaServiceImpl implements INominaService {

    private final ModelMapper modelMapper;

    private final INominaRepositoy repo;

    public NominaServiceImpl(ModelMapper modelMapper, INominaRepositoy repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }



    @Override
    public String spGenerarNominaMasiva(Integer pdIdPeriodo) {

        try{
            List<Mensaje> mensajes = repo
                    .spGenerarNominaMasiva(pdIdPeriodo);

            if (!mensajes.isEmpty()) {
                return mensajes.get(0).getMensaje(); // mensaje del SP
            }
            return "No se recibió respuesta del procedimiento.";
        }
        catch(DataAccessException e){
            // Extraer mensaje real si viene desde SQL Server
            String detalle = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();
            throw new DataBaseException(detalle);
        }
    }

    @Override
    @Transactional
    public MensajeDTO liquidarPeriodo(Integer idPeriodo) {
        try {
            List<Object[]> resultado = repo.spLiquidarPeriodo(idPeriodo);

            if (!resultado.isEmpty()) {
                Object[] fila = resultado.get(0);
                String estado = fila[0].toString();
                String mensaje = fila[1].toString();
                return new MensajeDTO(estado, mensaje);
            } else {
                return new MensajeDTO("ERROR", "No se recibió respuesta del procedimiento.");
            }

        } catch (DataAccessException e) {
            String detalle = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();
            throw new DataBaseException(detalle);
        }
    }


}
