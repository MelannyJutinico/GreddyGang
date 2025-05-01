package co.edu.unbosque.PayrollAPI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_novedad")
public class TipoNovedad {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_novedad")
    private Integer idTipoNovedad;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;

    //Constructores

    public TipoNovedad() {
    }

    public TipoNovedad(String nombre, String descripcion, Boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public TipoNovedad(Integer idTipoNovedad, String nombre, String descripcion, Boolean activo) {
        this.idTipoNovedad = idTipoNovedad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    //Getters & Setters

    public Integer getIdTipoNovedad() {
        return idTipoNovedad;
    }

    public void setIdTipoNovedad(Integer idTipoNovedad) {
        this.idTipoNovedad = idTipoNovedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

