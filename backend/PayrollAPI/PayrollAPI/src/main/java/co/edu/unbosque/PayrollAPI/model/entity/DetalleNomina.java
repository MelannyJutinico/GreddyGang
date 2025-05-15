package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_nomina")
public class DetalleNomina {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_nomina")
    private Nomina nomina;

    @ManyToOne
    @JoinColumn(name = "id_concepto")
    private ConceptoNomina conceptoNomina;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "observaciones")
    private String observaciones;

    //Constructores

    public DetalleNomina() {
    }

    public DetalleNomina(Nomina nomina, ConceptoNomina conceptoNomina, BigDecimal cantidad, BigDecimal valorUnitario, BigDecimal valorTotal, String observaciones) {
        this.nomina = nomina;
        this.conceptoNomina = conceptoNomina;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.observaciones = observaciones;
    }

    public DetalleNomina(Integer idDetalle, Nomina nomina, ConceptoNomina conceptoNomina, BigDecimal cantidad, BigDecimal valorUnitario, BigDecimal valorTotal, String observaciones) {
        this.idDetalle = idDetalle;
        this.nomina = nomina;
        this.conceptoNomina = conceptoNomina;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.observaciones = observaciones;
    }

    //Getters & Setters


    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Nomina getNomina() {
        return nomina;
    }

    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }

    public ConceptoNomina getConceptoNomina() {
        return conceptoNomina;
    }

    public void setConceptoNomina(ConceptoNomina conceptoNomina) {
        this.conceptoNomina = conceptoNomina;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}

