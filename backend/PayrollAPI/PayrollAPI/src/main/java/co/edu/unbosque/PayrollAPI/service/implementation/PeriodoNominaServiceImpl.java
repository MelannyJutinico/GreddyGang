package co.edu.unbosque.PayrollAPI.service.implementation;

import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PeriodoNominaDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import co.edu.unbosque.PayrollAPI.model.entity.PeriodoNomina;
import co.edu.unbosque.PayrollAPI.exception.exception.DataBaseException;
import co.edu.unbosque.PayrollAPI.repository.IPeriodoNominaRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IPeriodoNominaService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeriodoNominaServiceImpl implements IPeriodoNominaService {

    private final ModelMapper modelMapper;

    private final IPeriodoNominaRepository repo;

    public PeriodoNominaServiceImpl(ModelMapper modelMapper, IPeriodoNominaRepository repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }


    @Override
    public MensajeDTO spCrearPeriodo(String mesSeleccionado, String vvDescripcion) {
        try{

            java.time.YearMonth yearMonth = java.time.YearMonth.parse(mesSeleccionado);
            LocalDate pdFechaInicio = yearMonth.atDay(1);
            LocalDate pdFechaFin = yearMonth.atEndOfMonth();


            Mensaje mensaje = repo
                    .spCrearPeriodo(pdFechaInicio, pdFechaFin, vvDescripcion);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            return new MensajeDTO("NOT OK", "Error al crear periodo");
        }

    }

    @Override
    public MensajeDTO spCambiarEstadoPeriodo(Integer pnIdPeriodo, String vvNuevoEstado) {

        try{
            Mensaje mensaje = repo
                    .spCambiarEstadoPeriodo(pnIdPeriodo, vvNuevoEstado);

            return modelMapper
                    .map(mensaje, MensajeDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al cambiar el estado del periodo");
        }
    }

    @Override
    public PeriodoNominaDTO spBuscarPeriodoPorId(Integer pnIdPeriodo) {
        try{

            PeriodoNomina periodoNomina = repo
                    .spBuscarPeriodoPorId(pnIdPeriodo);

            return modelMapper
                    .map(periodoNomina, PeriodoNominaDTO.class);
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al cambiar el estado del periodo");
        }
    }

    @Override
    public List<PeriodoNominaDTO> vwPeriodoNomina() {
        try{
            return repo
                    .vwPeriodoNomina()
                    .stream()
                    .map((periodoNomina) -> modelMapper.map(periodoNomina, PeriodoNominaDTO.class))
                    .collect(Collectors.toList());
        }
        catch(DataAccessException e){
            throw new DataBaseException("Error al listar los periodos disponibles");
        }
    }

}
