package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IAportePatronalRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IAportePatronalService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AportePatronalServiceImpl implements IAportePatronalService {

    private final ModelMapper modelMapper;

    private final IAportePatronalRepository repo;

    public AportePatronalServiceImpl(ModelMapper modelMapper, IAportePatronalRepository repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }

    @Override
    public MensajeDTO spGenerarAportesPatronales(Integer pnIdPeriodo) {

        try{
            Mensaje mensaje = repo
                    .spGenerarAportesPatronales(pnIdPeriodo);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al generar aportes patronales");
        }

    }
}
