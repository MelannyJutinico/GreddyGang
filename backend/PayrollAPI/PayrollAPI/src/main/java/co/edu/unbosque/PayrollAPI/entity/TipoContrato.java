package co.edu.unbosque.PayrollAPI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_contrato")
public class TipoContrato {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_contrato")
    private Integer idTipoContrato;

    @Column(name = "nombre")
    private String nombre;

    //Constructores

    public TipoContrato() {
    }

    public TipoContrato(String nombre) {
        this.nombre = nombre;
    }

    public TipoContrato(Integer idTipoContrato, String nombre) {
        this.idTipoContrato = idTipoContrato;
        this.nombre = nombre;
    }

    //Getters & Setters

    public Integer getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(Integer idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
