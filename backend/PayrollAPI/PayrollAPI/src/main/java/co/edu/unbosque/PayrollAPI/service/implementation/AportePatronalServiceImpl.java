package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.entity.AportePatronalPeriodoView;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IAportePatronalRepository;
import co.edu.unbosque.PayrollAPI.repository.IAportePatronalViewRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IAportePatronalService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AportePatronalServiceImpl implements IAportePatronalService {

    private final ModelMapper modelMapper;

    private final IAportePatronalRepository repo;
    private final IAportePatronalViewRepository repoView;

    public AportePatronalServiceImpl(ModelMapper modelMapper, IAportePatronalRepository repo, IAportePatronalViewRepository repoView) {
        this.modelMapper = modelMapper;
        this.repo = repo;
        this.repoView = repoView;
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

    @Override
    public List<AportePatronalPeriodoView> getAportesByPeriodoAndEmployee(Integer idPeriodo, Integer idEmpleado) {
        try{
            return repoView
                    .findAportesByPeriodoAndEmpleado(idPeriodo, idEmpleado);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al listar aportes patronales");
        }
    }
}
