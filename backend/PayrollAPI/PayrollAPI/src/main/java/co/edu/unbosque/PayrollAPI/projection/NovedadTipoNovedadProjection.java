package co.edu.unbosque.PayrollAPI.projection;

import java.time.LocalDate;

public interface NovedadTipoNovedadProjection {

        Integer getIdNovedad();
        String getTipoNovedad();
        LocalDate getFechaInicio();
        LocalDate getFechaFin();
        String getObservacion();

}
