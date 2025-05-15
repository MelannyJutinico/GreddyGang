package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.entity.ProvisionPrestacionPeriodoView;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IProvisionPrestacionRepository;
import co.edu.unbosque.PayrollAPI.repository.IProvisionesViewRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IProvisionPrestacionService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvisionPrestacionServiceImpl implements IProvisionPrestacionService {

    private final ModelMapper modelMapper;
    private final IProvisionesViewRepository repoView;

    private final IProvisionPrestacionRepository repo;

    public ProvisionPrestacionServiceImpl(ModelMapper modelMapper, IProvisionesViewRepository repoView, IProvisionPrestacionRepository repo) {
        this.modelMapper = modelMapper;
        this.repoView = repoView;
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

    @Override
    public List<ProvisionPrestacionPeriodoView> getProvisionesByPeriodoAndEmployee(Integer idPeriodo, Integer idEmpleado) {
        try{
            return repoView
                    .findProvisionesByPeriodoAndEmpleado(idPeriodo, idEmpleado);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al listar provisiones");
        }
    }

}
