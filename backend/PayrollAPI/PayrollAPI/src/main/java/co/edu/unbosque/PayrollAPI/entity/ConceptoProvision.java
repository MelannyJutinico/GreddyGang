package co.edu.unbosque.PayrollAPI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "concepto_provision")
public class ConceptoProvision {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepto_provision")
    private Integer idConceptoProvision;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    //Constructores

    public ConceptoProvision() {
    }

    public ConceptoProvision(String nombre, Boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }

    public ConceptoProvision(Integer idConceptoProvision, String nombre, Boolean activo) {
        this.idConceptoProvision = idConceptoProvision;
        this.nombre = nombre;
        this.activo = activo;
    }

    //Getters & Setters

    public Integer getIdConceptoProvision() {
        return idConceptoProvision;
    }

    public void setIdConceptoProvision(Integer idConceptoProvision) {
        this.idConceptoProvision = idConceptoProvision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}

