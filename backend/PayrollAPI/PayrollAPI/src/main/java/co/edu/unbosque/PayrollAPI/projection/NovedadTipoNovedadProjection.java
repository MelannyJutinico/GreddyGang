package co.edu.unbosque.PayrollAPI.projection;

import java.time.LocalDate;

public interface NovedadTipoNovedadProjection {

        String getTipoNovedad();
        LocalDate getFechaInicio();
        LocalDate getFechaFin();
        String getObservacion();

}
