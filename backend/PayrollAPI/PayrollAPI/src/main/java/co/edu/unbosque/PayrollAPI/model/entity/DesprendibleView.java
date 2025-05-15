package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vw_desprendible_nomina")
public class DesprendibleView {


    @Id
    @Column(name = "id_row")
    private Long idRow;


    @Column(name = "id_nomina")
    private Long idNomina;

    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "salario_base")
    private BigDecimal salarioBase;

    @Column(name = "recibe_auxilio_transporte")
    private Boolean recibeAuxilioTransporte;

    @Column(name = "id_periodo")
    private Long idPeriodo;

    @Column(name = "periodo_descripcion")
    private String periodoDescripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "fecha_liquidacion")
    private LocalDate fechaLiquidacion;

    @Column(name = "concepto")
    private String concepto;

    @Column(name = "tipo_concepto")
    private String tipoConcepto;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "total_devengado")
    private BigDecimal totalDevengado;

    @Column(name = "total_deduccion")
    private BigDecimal totalDeduccion;

    @Column(name = "total_a_pagar")
    private BigDecimal totalAPagar;

    @Column(name = "dias_trabajados")
    private Integer diasTrabajados;

    public Integer getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(Integer diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

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

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Boolean getRecibeAuxilioTransporte() {
        return recibeAuxilioTransporte;
    }

    public void setRecibeAuxilioTransporte(Boolean recibeAuxilioTransporte) {
        this.recibeAuxilioTransporte = recibeAuxilioTransporte;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getPeriodoDescripcion() {
        return periodoDescripcion;
    }

    public void setPeriodoDescripcion(String periodoDescripcion) {
        this.periodoDescripcion = periodoDescripcion;
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

    public LocalDate getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(LocalDate fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(String tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
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
}
