package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.dto.regular.TipoConceptoDTO;
import co.edu.unbosque.PayrollAPI.entity.TipoConcepto;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.ITipoConceptoRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.ITipoConceptoService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoConceptoServiceImpl implements ITipoConceptoService {

    private final ITipoConceptoRepository repo;
    private final ModelMapper modelMapper;

    public TipoConceptoServiceImpl(ITipoConceptoRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TipoConceptoDTO> listarTipoConcepto() {

        try{
            List<TipoConcepto> tiposConcepto = (List<TipoConcepto>) repo.findAll();
            return tiposConcepto
                    .stream()
                    .map((tipoConcepto) -> modelMapper.map(tipoConcepto, TipoConceptoDTO.class))
                    .collect(Collectors.toList());
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al listar tipos de conceptos");
        }

    }
}
