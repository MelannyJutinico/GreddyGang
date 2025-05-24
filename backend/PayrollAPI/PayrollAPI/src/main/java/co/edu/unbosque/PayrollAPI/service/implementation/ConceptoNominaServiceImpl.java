package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.ConceptoNominaDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IConceptoNominaRepository;
import co.edu.unbosque.PayrollAPI.repository.INominaRepositoy;
import co.edu.unbosque.PayrollAPI.service.interfaces.IConceptoNominaService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConceptoNominaServiceImpl implements IConceptoNominaService {

    private final IConceptoNominaRepository repo;
    private final ModelMapper modelMapper;
    private final INominaRepositoy nominaRepository;

    public ConceptoNominaServiceImpl(IConceptoNominaRepository repo, ModelMapper modelMapper, INominaRepositoy nominaRepository) {
        this.repo = repo;
        this.modelMapper = modelMapper;
        this.nominaRepository = nominaRepository;
    }

    @Override
    public List<ConceptoNominaDTO> vwConceptosDevengadosDeduccion() {
        try {
            return repo.vwConceptosDevengadosDeduccion()
                    .stream()
                    .map(p -> {
                        ConceptoNominaDTO dto = new ConceptoNominaDTO();
                        dto.setIdConcepto(p.getIdConcepto());
                        dto.setNombre(p.getNombre());
                        dto.setIdTipoConcepto(p.getIdTipoConcepto());
                        dto.setTipoConcepto(p.getTipoConcepto());
                        dto.setDescripcion(p.getDescripcion());
                        return dto;
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new DataBaseException("Error al listar conceptos");
        }
    }


    @Override
    public List<ConceptoNominaDTO> vwConceptosHorasExtra() {
        try{
            return repo
                    .vwConceptosHorasExtra()
                    .stream()
                    .map((projection) -> modelMapper.map(projection, ConceptoNominaDTO.class))
                    .collect(Collectors.toList());
        }
        catch(DataAccessException e){
            throw  new DataBaseException("Error al listar conceptos");
        }
    }



    @Override
    @Transactional
    public MensajeDTO spAgregarHorasExtras(Integer idPeriodo, Integer idEmpleado, Integer idConcepto, BigDecimal cantidadHoras) {
        try {
            Integer idNomina = nominaRepository.obtenerIdNominaPorEmpleadoYPeriodo(idEmpleado, idPeriodo);
            if (idNomina == null) {
                throw new DataBaseException("No se encontró la nómina del empleado para este período.");
            }

            repo.spAgregarHorasExtras(idNomina, idEmpleado, idConcepto, cantidadHoras);

            return new MensajeDTO("OK", "Horas extra agregadas exitosamente.");
        } catch (DataAccessException e) {
            String detalle = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();
            throw new DataBaseException(detalle);
        }
    }

}
