package co.edu.unbosque.PayrollAPI.model.dto.complex;

import java.time.LocalDate;

public class NovedadTipoNovedadDTO {

    private Integer idNovedad;
    private String tipoNovedad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String observacion;


    public NovedadTipoNovedadDTO() {
    }

    public NovedadTipoNovedadDTO(Integer idNovedad,String tipoNovedad, LocalDate fechaInicio, LocalDate fechaFin, String observacion) {
        this.idNovedad = idNovedad;
        this.tipoNovedad = tipoNovedad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observacion = observacion;
    }

    public Integer getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Integer idNovedad) {
        this.idNovedad = idNovedad;
    }

    public String getTipoNovedad() {
        return tipoNovedad;
    }

    public void setTipoNovedad(String tipoNovedad) {
        this.tipoNovedad = tipoNovedad;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
