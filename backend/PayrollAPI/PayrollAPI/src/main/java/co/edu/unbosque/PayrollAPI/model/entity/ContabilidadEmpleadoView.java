
package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vw_contabilidad_empleado")
public class ContabilidadEmpleadoView {

    @Id
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "salario_base")
    private BigDecimal salarioBase;

    @Column(name = "id_periodo")
    private Integer idPeriodo;

    @Column(name = "periodo_descripcion")
    private String periodoDescripcion;

    @Column(name = "total_aportes")
    private BigDecimal totalAportes;

    @Column(name = "total_provisiones")
    private BigDecimal totalProvisiones;

    @Column(name = "total_salario")
    private BigDecimal totalSalario;

    @Column(name = "total_costo_empresa")
    private BigDecimal totalCostoEmpresa;

    // Getters y setters
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public String getPeriodoDescripcion() {
        return periodoDescripcion;
    }

    public void setPeriodoDescripcion(String periodoDescripcion) {
        this.periodoDescripcion = periodoDescripcion;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public BigDecimal getTotalAportes() {
        return totalAportes;
    }

    public void setTotalAportes(BigDecimal totalAportes) {
        this.totalAportes = totalAportes;
    }

    public BigDecimal getTotalProvisiones() {
        return totalProvisiones;
    }

    public void setTotalProvisiones(BigDecimal totalProvisiones) {
        this.totalProvisiones = totalProvisiones;
    }

    public BigDecimal getTotalSalario() {
        return totalSalario;
    }

    public void setTotalSalario(BigDecimal totalSalario) {
        this.totalSalario = totalSalario;
    }

    public BigDecimal getTotalCostoEmpresa() {
        return totalCostoEmpresa;
    }

    public void setTotalCostoEmpresa(BigDecimal totalCostoEmpresa) {
        this.totalCostoEmpresa = totalCostoEmpresa;
    }
}
