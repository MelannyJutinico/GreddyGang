package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "parametro_nomina")
public class ParametroNomina {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro")
    private Integer idParametro;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valor_numerico")
    private BigDecimal valorNumerico;

    @Column(name = "valor_texto")
    private String valorTexto;

    @Column(name = "activo")
    private Boolean activo;

    //Constructores

    public ParametroNomina() {
    }

    public ParametroNomina(String nombre, BigDecimal valorNumerico, String valorTexto, Boolean activo) {
        this.nombre = nombre;
        this.valorNumerico = valorNumerico;
        this.valorTexto = valorTexto;
        this.activo = activo;
    }

    public ParametroNomina(Integer idParametro, String nombre, BigDecimal valorNumerico, String valorTexto, Boolean activo) {
        this.idParametro = idParametro;
        this.nombre = nombre;
        this.valorNumerico = valorNumerico;
        this.valorTexto = valorTexto;
        this.activo = activo;
    }

    //Getters & Setters

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(BigDecimal valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public String getValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(String valorTexto) {
        this.valorTexto = valorTexto;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

