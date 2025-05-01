package co.edu.unbosque.PayrollAPI.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "novedad")
public class Novedad {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_novedad")
    private Integer idNovedad;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_tipo_novedad")
    private TipoNovedad tipoNovedad;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "porcentaje_pago")
    private BigDecimal porcentajePago;

    @Column(name = "observacion")
    private String observacion;

    //Constructores

    public Novedad() {
    }

    public Novedad(Empleado empleado, TipoNovedad tipoNovedad, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion) {
        this.empleado = empleado;
        this.tipoNovedad = tipoNovedad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentajePago = porcentajePago;
        this.observacion = observacion;
    }

    public Novedad(Integer idNovedad, Empleado empleado, TipoNovedad tipoNovedad, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal porcentajePago, String observacion) {
        this.idNovedad = idNovedad;
        this.empleado = empleado;
        this.tipoNovedad = tipoNovedad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentajePago = porcentajePago;
        this.observacion = observacion;
    }

    //Getters & Setters

    public Integer getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Integer idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public TipoNovedad getTipoNovedad() {
        return tipoNovedad;
    }

    public void setTipoNovedad(TipoNovedad tipoNovedad) {
        this.tipoNovedad = tipoNovedad;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getPorcentajePago() {
        return porcentajePago;
    }

    public void setPorcentajePago(BigDecimal porcentajePago) {
        this.porcentajePago = porcentajePago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}

