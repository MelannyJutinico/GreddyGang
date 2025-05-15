package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.ICesantiaPagadaRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.ICesantiaPagadaService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CesantiaPagadaServiceImpl implements ICesantiaPagadaService {

    private final ModelMapper modelMapper;

    private final ICesantiaPagadaRepository repo;

    public CesantiaPagadaServiceImpl(ModelMapper modelMapper, ICesantiaPagadaRepository repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }

    @Override
    public MensajeDTO spRegistrarCesantiasPagadas(Integer pnIdPeriodo) {

        try{
            Mensaje mensaje = repo
                    .spRegistrarCesantiasPagadas(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al registrar las cesantias pagadas");
        }

    }

}
