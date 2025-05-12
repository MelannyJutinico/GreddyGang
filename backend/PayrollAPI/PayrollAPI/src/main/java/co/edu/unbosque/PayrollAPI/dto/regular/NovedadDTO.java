package co.edu.unbosque.PayrollAPI.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

//Atributos

public class NovedadDTO {

    private Integer idNovedad;
    private EmpleadoDTO empleadoDTO;
    private TipoNovedadDTO tipoNovedadDTO;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal porcentajePago;
    private String observacion;

    //Constructores

    public NovedadDTO() {
    }

    public NovedadDTO(EmpleadoDTO empleadoDTO, TipoNovedadDTO tipoNovedadDTO, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion) {
        this.empleadoDTO = empleadoDTO;
        this.tipoNovedadDTO = tipoNovedadDTO;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentajePago = porcentajePago;
        this.observacion = observacion;
    }

    public NovedadDTO(Integer idNovedad, EmpleadoDTO empleadoDTO, TipoNovedadDTO tipoNovedadDTO, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion) {
        this.idNovedad = idNovedad;
        this.empleadoDTO = empleadoDTO;
        this.tipoNovedadDTO = tipoNovedadDTO;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentajePago = porcentajePago;
        this.observacion = observacion;
    }

    //Getters & Setters


    public Integer getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Integer idNovedad) {
        this.idNovedad = idNovedad;
    }

    public EmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

    public TipoNovedadDTO getTipoNovedadDTO() {
        return tipoNovedadDTO;
    }

    public void setTipoNovedadDTO(TipoNovedadDTO tipoNovedadDTO) {
        this.tipoNovedadDTO = tipoNovedadDTO;
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

    public BigDecimal getPorcentajePago() {
        return porcentajePago;
    }

    public void setPorcentajePago(BigDecimal porcentajePago) {
        this.porcentajePago = porcentajePago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
