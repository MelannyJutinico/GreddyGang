package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.dto.regular.TipoNovedadDTO;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.ITipoNovedadRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.ITipoNovedadService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoNovedadServiceImpl implements ITipoNovedadService {

    private final ITipoNovedadRepository repo;

    private final ModelMapper modelMapper;


    public TipoNovedadServiceImpl(ITipoNovedadRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TipoNovedadDTO> vwTipoNovedadActivo() {
        try{
            return repo
                    .vwTipoNovedadActivo()
                    .stream()
                    .map((tipoNovedad) -> modelMapper.map(tipoNovedad, TipoNovedadDTO.class))
                    .collect(Collectors.toList());
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al listar los tipos de novedades");
        }
    }
}
