package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "aporte_patronal")
public class AportePatronal {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aporte")
    private Integer idAporte;

    @ManyToOne
    @JoinColumn(name = "id_nomina")
    private Nomina nomina;

    @ManyToOne
    @JoinColumn(name = "id_tipo_aporte")
    private TipoAportePatronal tipoAportePatronal;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    //Constuctores

    public AportePatronal() {
    }

    public AportePatronal(Nomina nomina, TipoAportePatronal tipoAportePatronal, BigDecimal cantidad) {
        this.nomina = nomina;
        this.tipoAportePatronal = tipoAportePatronal;
        this.cantidad = cantidad;
    }

    public AportePatronal(Integer idAporte, Nomina nomina, TipoAportePatronal tipoAportePatronal, BigDecimal cantidad) {
        this.idAporte = idAporte;
        this.nomina = nomina;
        this.tipoAportePatronal = tipoAportePatronal;
        this.cantidad = cantidad;
    }

    //Getters & Setters

    public Integer getIdAporte() {
        return idAporte;
    }

    public void setIdAporte(Integer idAporte) {
        this.idAporte = idAporte;
    }

    public Nomina getNomina() {
        return nomina;
    }

    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }

    public TipoAportePatronal getTipoAportePatronal() {
        return tipoAportePatronal;
    }

    public void setTipoAportePatronal(TipoAportePatronal tipoAportePatronal) {
        this.tipoAportePatronal = tipoAportePatronal;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}

