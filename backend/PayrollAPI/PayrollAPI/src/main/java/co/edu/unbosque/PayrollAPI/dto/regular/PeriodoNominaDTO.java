package co.edu.unbosque.PayrollAPI.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PeriodoNominaDTO {

    //Atributos

    private Integer idPeriodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;

    //Constructores

    public PeriodoNominaDTO() {
    }

    public PeriodoNominaDTO(LocalDate fechaInicio, LocalDate fechaFin, String descripcion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }


    public PeriodoNominaDTO(Integer idPeriodo, LocalDate fechaInicio, LocalDate fechaFin, String descripcion, String estado, LocalDateTime fechaCreacion) {
        this.idPeriodo = idPeriodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    //Getters & Setters

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}

