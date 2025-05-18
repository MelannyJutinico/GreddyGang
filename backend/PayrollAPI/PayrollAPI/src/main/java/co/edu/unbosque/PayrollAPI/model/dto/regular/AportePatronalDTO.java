package co.edu.unbosque.PayrollAPI.model.dto.regular;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AportePatronalDTO {
    private Integer idEmpleado;
    private String nombreEmpleado;
    private String tipoContrato;
    private String nivelRiesgo;
    private BigDecimal salarioBase;
    private Integer idPeriodo;
    private String descripcionPeriodo;
    private String tipoAporte;
    private BigDecimal cantidad;
    private LocalDate fechaLiquidacion;

    public AportePatronalDTO() {
    }

    public AportePatronalDTO(Integer idEmpleado, String nombreEmpleado, String tipoContrato, String nivelRiesgo, BigDecimal salarioBase, Integer idPeriodo, String descripcionPeriodo, String tipoAporte, BigDecimal cantidad, LocalDate fechaLiquidacion) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.tipoContrato = tipoContrato;
        this.nivelRiesgo = nivelRiesgo;
        this.salarioBase = salarioBase;
        this.idPeriodo = idPeriodo;
        this.descripcionPeriodo = descripcionPeriodo;
        this.tipoAporte = tipoAporte;
        this.cantidad = cantidad;
        this.fechaLiquidacion = fechaLiquidacion;
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

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getDescripcionPeriodo() {
        return descripcionPeriodo;
    }

    public void setDescripcionPeriodo(String descripcionPeriodo) {
        this.descripcionPeriodo = descripcionPeriodo;
    }

    public String getTipoAporte() {
        return tipoAporte;
    }

    public void setTipoAporte(String tipoAporte) {
        this.tipoAporte = tipoAporte;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(LocalDate fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }
}
