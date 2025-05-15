package co.edu.unbosque.PayrollAPI.model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vw_nomina_resumen")
public class NominaResumenView {

    @Id
    @Column(name = "id_nomina")
    private Long idNomina;

    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "id_periodo")
    private String periodo;


    @Column(name = "periodo_descripcion")
    private String descripcionPeriodo;

    @Column(name = "total_devengado")
    private BigDecimal totalDevengado;

    @Column(name = "total_deduccion")
    private BigDecimal totalDeduccion;

    @Column(name = "total_a_pagar")
    private BigDecimal totalAPagar;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_liquidacion")
    private LocalDate fechaLiquidacion;




    public Long getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Long idNomina) {
        this.idNomina = idNomina;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDescripcionPeriodo() {
        return descripcionPeriodo;
    }

    public void setDescripcionPeriodo(String descripcionPeriodo) {
        this.descripcionPeriodo = descripcionPeriodo;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    public LocalDate getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(LocalDate fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }
}
