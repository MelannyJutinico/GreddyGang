package co.edu.unbosque.PayrollAPI.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "provision_prestaciones")
public class ProvisionPrestacion {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provision")
    private Integer idProvision;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_periodo")
    private PeriodoNomina periodoNomina;

    @ManyToOne
    @JoinColumn(name = "id_concepto_provision")
    private ConceptoProvision conceptoProvision;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    //Constructores

    public ProvisionPrestacion() {
    }

    public ProvisionPrestacion(Empleado empleado, PeriodoNomina periodoNomina, ConceptoProvision conceptoProvision, BigDecimal cantidad, LocalDateTime fechaRegistro) {
        this.empleado = empleado;
        this.periodoNomina = periodoNomina;
        this.conceptoProvision = conceptoProvision;
        this.cantidad = cantidad;
        this.fechaRegistro = fechaRegistro;
    }

    public ProvisionPrestacion(Integer idProvision, Empleado empleado, PeriodoNomina periodoNomina, ConceptoProvision conceptoProvision, BigDecimal cantidad, LocalDateTime fechaRegistro) {
        this.idProvision = idProvision;
        this.empleado = empleado;
        this.periodoNomina = periodoNomina;
        this.conceptoProvision = conceptoProvision;
        this.cantidad = cantidad;
        this.fechaRegistro = fechaRegistro;
    }

    //Getters & Setters

    public Integer getIdProvision() {
        return idProvision;
    }

    public void setIdProvision(Integer idProvision) {
        this.idProvision = idProvision;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public PeriodoNomina getPeriodoNomina() {
        return periodoNomina;
    }

    public void setPeriodoNomina(PeriodoNomina periodoNomina) {
        this.periodoNomina = periodoNomina;
    }

    public ConceptoProvision getConceptoProvision() {
        return conceptoProvision;
    }

    public void setConceptoProvision(ConceptoProvision conceptoProvision) {
        this.conceptoProvision = conceptoProvision;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

