package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vw_contabilidad_totales_periodo")
public class ContabilidadTotalesPeriodoView {

    @Id
    @Column(name = "id_periodo")
    private Integer idPeriodo;

    @Column(name = "total_salarios")
    private BigDecimal totalSalarios;

    @Column(name = "total_aportes")
    private BigDecimal totalAportes;

    @Column(name = "total_provisiones")
    private BigDecimal totalProvisiones;

    @Column(name = "total_general")
    private BigDecimal totalGeneral;

    @Column(name = "porcentaje_salarios")
    private BigDecimal porcentajeSalarios;

    @Column(name = "porcentaje_aportes")
    private BigDecimal porcentajeAportes;

    @Column(name = "porcentaje_provisiones")
    private BigDecimal porcentajeProvisiones;

    // Getters y Setters


    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public BigDecimal getTotalSalarios() {
        return totalSalarios;
    }

    public void setTotalSalarios(BigDecimal totalSalarios) {
        this.totalSalarios = totalSalarios;
    }

    public BigDecimal getTotalAportes() {
        return totalAportes;
    }

    public void setTotalAportes(BigDecimal totalAportes) {
        this.totalAportes = totalAportes;
    }

    public BigDecimal getTotalProvisiones() {
        return totalProvisiones;
    }

    public void setTotalProvisiones(BigDecimal totalProvisiones) {
        this.totalProvisiones = totalProvisiones;
    }

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public BigDecimal getPorcentajeSalarios() {
        return porcentajeSalarios;
    }

    public void setPorcentajeSalarios(BigDecimal porcentajeSalarios) {
        this.porcentajeSalarios = porcentajeSalarios;
    }

    public BigDecimal getPorcentajeAportes() {
        return porcentajeAportes;
    }

    public void setPorcentajeAportes(BigDecimal porcentajeAportes) {
        this.porcentajeAportes = porcentajeAportes;
    }

    public BigDecimal getPorcentajeProvisiones() {
        return porcentajeProvisiones;
    }

    public void setPorcentajeProvisiones(BigDecimal porcentajeProvisiones) {
        this.porcentajeProvisiones = porcentajeProvisiones;
    }
}
