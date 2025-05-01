package co.edu.unbosque.PayrollAPI.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "nivel_riesgo_arl")
public class NivelRiesgoArl {

    //Atributos

    @Id
    @Column(name = "id_nivel")
    private Integer idNivel;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "porcentaje")
    private BigDecimal porcentaje;

    //Constructores

    public NivelRiesgoArl() {
    }

    public NivelRiesgoArl(String nombre, BigDecimal porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public NivelRiesgoArl(Integer idNivel, String nombre, BigDecimal porcentaje) {
        this.idNivel = idNivel;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    //Getters & Setters


    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }
}
