package co.edu.unbosque.PayrollAPI.model.dto.regular;

import java.math.BigDecimal;

public class PrimaDTO {
    private Integer idPeriodo;
    private Integer idEmpleado;
    private String nombreEmpleado;
    private BigDecimal valorPrima;
    private String observaciones;

    public PrimaDTO(Integer idPeriodo, Integer idEmpleado,  String nombreEmpleado, BigDecimal valorPrima, String observaciones) {
        this.idPeriodo = idPeriodo;
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.valorPrima = valorPrima;
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public PrimaDTO() {
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }



    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public BigDecimal getValorPrima() {
        return valorPrima;
    }

    public void setValorPrima(BigDecimal valorPrima) {
        this.valorPrima = valorPrima;
    }
}