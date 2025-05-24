package co.edu.unbosque.PayrollAPI.model.dto.regular;

import java.math.BigDecimal;

public class CesantiaPagadaDTO {
    private Integer idPeriodo;
    private Integer idEmpleado;
    private String nombreEmpleado;
    private BigDecimal salarioBase;
    private Integer diasTrabajados;
    private BigDecimal valorCesantia;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
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

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Integer getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(Integer diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public BigDecimal getValorCesantia() {
        return valorCesantia;
    }

    public void setValorCesantia(BigDecimal valorCesantia) {
        this.valorCesantia = valorCesantia;
    }
}