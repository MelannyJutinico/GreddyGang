package co.edu.unbosque.PayrollAPI.service.interfaces;

import co.edu.unbosque.PayrollAPI.model.entity.TipoContrato;
import java.util.List;

public interface ITipoContratoService {
    List<TipoContrato> obtenerTodos();
}
