package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cesantias_pagadas")
public class CesantiaPagada {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_periodo")
    private PeriodoNomina periodo;

    @Column(name = "salario_base")
    private BigDecimal salarioBase;

    @Column(name = "dias_trabajados")
    private Integer diasTrabajados;

    @Column(name = "valor_cesantias")
    private BigDecimal valorCesantias;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    //Constructores

    public CesantiaPagada() {
    }

    public CesantiaPagada(Empleado empleado, PeriodoNomina periodo, BigDecimal salarioBase, Integer diasTrabajados, BigDecimal valorCesantias, LocalDateTime fechaRegistro) {
        this.empleado = empleado;
        this.periodo = periodo;
        this.salarioBase = salarioBase;
        this.diasTrabajados = diasTrabajados;
        this.valorCesantias = valorCesantias;
        this.fechaRegistro = fechaRegistro;
    }

    public CesantiaPagada(Integer idPago, Empleado empleado, PeriodoNomina periodo, BigDecimal salarioBase, Integer diasTrabajados, BigDecimal valorCesantias, LocalDateTime fechaRegistro) {
        this.idPago = idPago;
        this.empleado = empleado;
        this.periodo = periodo;
        this.salarioBase = salarioBase;
        this.diasTrabajados = diasTrabajados;
        this.valorCesantias = valorCesantias;
        this.fechaRegistro = fechaRegistro;
    }

    //Getters & Setters

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public PeriodoNomina getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoNomina periodo) {
        this.periodo = periodo;
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

    public BigDecimal getValorCesantias() {
        return valorCesantias;
    }

    public void setValorCesantias(BigDecimal valorCesantias) {
        this.valorCesantias = valorCesantias;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
