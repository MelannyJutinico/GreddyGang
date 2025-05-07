package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.dto.MensajeDTO;
import co.edu.unbosque.PayrollAPI.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IProvisionPrestacionRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IProvisionPrestacionService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProvisionPrestacionServiceImpl implements IProvisionPrestacionService {

    private final ModelMapper modelMapper;

    private final IProvisionPrestacionRepository repo;

    public ProvisionPrestacionServiceImpl(ModelMapper modelMapper, IProvisionPrestacionRepository repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }

    @Override
    public MensajeDTO spGenerarProvisiones(Integer pnIdPeriodo) {

        try{
            Mensaje mensaje = repo
                    .spGenerarProvisiones(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al generar provisiones");
        }
    }

}
