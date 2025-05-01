package co.edu.unbosque.PayrollAPI.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "concepto_nomina")
public class ConceptoNomina {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepto")
    private Integer idConcepto;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo_concepto")
    private TipoConcepto tipoConcepto;

    @Column(name = "registro_manual")
    private Boolean registroManual;

    @Column(name = "porcentaje")
    private BigDecimal porcentaje;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;

    //Constructores

    public ConceptoNomina() {
    }

    public ConceptoNomina(String nombre, TipoConcepto tipoConcepto, Boolean registroManual, BigDecimal porcentaje, String descripcion, Boolean activo) {
        this.nombre = nombre;
        this.tipoConcepto = tipoConcepto;
        this.registroManual = registroManual;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public ConceptoNomina(Integer idConcepto, String nombre, TipoConcepto tipoConcepto, Boolean registroManual, BigDecimal porcentaje, String descripcion, Boolean activo) {
        this.idConcepto = idConcepto;
        this.nombre = nombre;
        this.tipoConcepto = tipoConcepto;
        this.registroManual = registroManual;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    //Getters & Setters


    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoConcepto getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(TipoConcepto tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public Boolean getRegistroManual() {
        return registroManual;
    }

    public void setRegistroManual(Boolean registroManual) {
        this.registroManual = registroManual;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

