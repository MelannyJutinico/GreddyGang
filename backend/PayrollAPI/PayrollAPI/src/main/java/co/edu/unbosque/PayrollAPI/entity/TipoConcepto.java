package co.edu.unbosque.PayrollAPI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_concepto")
public class TipoConcepto {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_concepto")
    private Integer idTipoConcepto;

    @Column(name = "nombre")
    private String nombre;

    //Constructores

    public TipoConcepto() {
    }

    public TipoConcepto(String nombre) {
        this.nombre = nombre;
    }

    public TipoConcepto(Integer idTipoConcepto, String nombre) {
        this.idTipoConcepto = idTipoConcepto;
        this.nombre = nombre;
    }

    //Getters & Setters


    public Integer getIdTipoConcepto() {
        return idTipoConcepto;
    }

    public void setIdTipoConcepto(Integer idTipoConcepto) {
        this.idTipoConcepto = idTipoConcepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
