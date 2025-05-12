package co.edu.unbosque.PayrollAPI.projection;

import java.math.BigDecimal;

public interface ConceptoHoraExtraProjection {

    Integer getIdConcepto();
    String getNombre();
    BigDecimal getPorcentaje();
    String getDescripcion();
    Boolean getActivo();


}
