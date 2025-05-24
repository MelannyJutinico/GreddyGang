package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "log_novedad")
public class LogNovedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogNovedad;

    private Integer idNovedad;
    private Integer idEmpleado;
    private Integer idTipoNovedad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double porcentajePago;
    private String observacion;
    private String accion;
    private String motivo;
    private String usuario;
    private LocalDateTime fechaLog;

    public Integer getIdLogNovedad() {
        return idLogNovedad;
    }

    public void setIdLogNovedad(Integer idLogNovedad) {
        this.idLogNovedad = idLogNovedad;
    }

    public Integer getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Integer idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdTipoNovedad() {
        return idTipoNovedad;
    }

    public void setIdTipoNovedad(Integer idTipoNovedad) {
        this.idTipoNovedad = idTipoNovedad;
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

    public Double getPorcentajePago() {
        return porcentajePago;
    }

    public void setPorcentajePago(Double porcentajePago) {
        this.porcentajePago = porcentajePago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaLog() {
        return fechaLog;
    }

    public void setFechaLog(LocalDateTime fechaLog) {
        this.fechaLog = fechaLog;
    }
}
