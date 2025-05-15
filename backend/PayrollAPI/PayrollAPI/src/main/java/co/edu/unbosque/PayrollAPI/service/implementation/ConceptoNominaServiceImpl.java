package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.dto.regular.ConceptoNominaDTO;
import co.edu.unbosque.PayrollAPI.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IConceptoNominaRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IConceptoNominaService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConceptoNominaServiceImpl implements IConceptoNominaService {

    private final IConceptoNominaRepository repo;
    private final ModelMapper modelMapper;

    public ConceptoNominaServiceImpl(IConceptoNominaRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ConceptoNominaDTO> vwConceptosDevengadosDeduccion() {
        try{
            return repo
                    .vwConceptosDevengadosDeduccion()
                    .stream()
                    .map((projection) -> modelMapper.map(projection, ConceptoNominaDTO.class))
                    .collect(Collectors.toList());
        }
        catch(DataAccessException e){
            throw  new DataBaseException("Error al listar conceptos");
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
    public MensajeDTO spAgregarConcepto(Integer pnIdNomina, Integer pnIdConcepto, BigDecimal pnValorTotal) {

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
