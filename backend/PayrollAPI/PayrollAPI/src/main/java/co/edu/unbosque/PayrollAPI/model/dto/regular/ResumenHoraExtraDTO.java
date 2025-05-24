package co.edu.unbosque.PayrollAPI.model.dto.regular;

import java.math.BigDecimal;

public class ResumenHoraExtraDTO {

    private Integer idDetalle;
    private Integer idNomina;
    private Integer idPeriodo;
    private Integer idEmpleado;
    private String empleado;
    private String conceptoHoraExtra;
    private BigDecimal horas;
    private BigDecimal valorHora;
    private BigDecimal valorTotal;

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
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

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getConceptoHoraExtra() {
        return conceptoHoraExtra;
    }

    public void setConceptoHoraExtra(String conceptoHoraExtra) {
        this.conceptoHoraExtra = conceptoHoraExtra;
    }

    public BigDecimal getHoras() {
        return horas;
    }

    public void setHoras(BigDecimal horas) {
        this.horas = horas;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
