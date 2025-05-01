package co.edu.unbosque.PayrollAPI.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tipo_aporte_patronal")
public class TipoAportePatronal {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_aporte")
    private Integer idTipoAporte;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "porcentaje")
    private BigDecimal porcentaje;

    @Column(name = "activo")
    private Boolean activo;

    //Constructores

    public TipoAportePatronal() {
    }

    public TipoAportePatronal(String nombre, BigDecimal porcentaje, Boolean activo) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.activo = activo;
    }

    public TipoAportePatronal(Integer idTipoAporte, String nombre, BigDecimal porcentaje, Boolean activo) {
        this.idTipoAporte = idTipoAporte;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.activo = activo;
    }

    //Getters & Setters


    public Integer getIdTipoAporte() {
        return idTipoAporte;
    }

    public void setIdTipoAporte(Integer idTipoAporte) {
        this.idTipoAporte = idTipoAporte;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

