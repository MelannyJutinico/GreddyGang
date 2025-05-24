package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "nomina")
public class Nomina {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nomina")
    private Integer idNomina;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_periodo")
    private PeriodoNomina periodoNomina;

    @Column(name = "fecha_liquidacion")
    private LocalDate fechaLiquidacion;

    @Column(name = "total_devengado")
    private BigDecimal totalDevengado;

    @Column(name = "total_deduccion")
    private BigDecimal totalDeduccion;

    @Column(name = "total_a_pagar")
    private BigDecimal totalAPagar;

    @Column(name = "estado")
    private String estado;

    //Constructores

    public Nomina() {
    }

    public Nomina(Empleado empleado, PeriodoNomina periodoNomina, LocalDate fechaLiquidacion, BigDecimal totalDevengado, BigDecimal totalDeduccion, BigDecimal totalAPagar, String estado) {
        this.empleado = empleado;
        this.periodoNomina = periodoNomina;
        this.fechaLiquidacion = fechaLiquidacion;
        this.totalDevengado = totalDevengado;
        this.totalDeduccion = totalDeduccion;
        this.totalAPagar = totalAPagar;
        this.estado = estado;
    }

    public Nomina(Integer idNomina, Empleado empleado, PeriodoNomina periodoNomina, LocalDate fechaLiquidacion, BigDecimal totalDevengado, BigDecimal totalDeduccion, BigDecimal totalAPagar, String estado) {
        this.idNomina = idNomina;
        this.empleado = empleado;
        this.periodoNomina = periodoNomina;
        this.fechaLiquidacion = fechaLiquidacion;
        this.totalDevengado = totalDevengado;
        this.totalDeduccion = totalDeduccion;
        this.totalAPagar = totalAPagar;
        this.estado = estado;
    }

    //Getters & Setters

    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public PeriodoNomina getPeriodoNomina() {
        return periodoNomina;
    }

    public void setPeriodoNomina(PeriodoNomina periodoNomina) {
        this.periodoNomina = periodoNomina;
    }

    public LocalDate getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(LocalDate fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public BigDecimal getTotalDevengado() {
        return totalDevengado;
    }

    public void setTotalDevengado(BigDecimal totalDevengado) {
        this.totalDevengado = totalDevengado;
    }

    public BigDecimal getTotalDeduccion() {
        return totalDeduccion;
    }

    public void setTotalDeduccion(BigDecimal totalDeduccion) {
        this.totalDeduccion = totalDeduccion;
    }

    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

