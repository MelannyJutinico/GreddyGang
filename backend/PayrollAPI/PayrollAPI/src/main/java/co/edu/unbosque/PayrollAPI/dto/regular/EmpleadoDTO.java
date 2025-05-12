package co.edu.unbosque.PayrollAPI.dto.regular;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmpleadoDTO {

    // Atributos

    private Integer idEmpleado;
    private String nombre;
    private String cargo;
    private String departamento;
    private BigDecimal salarioBase;
    private Boolean recibeAuxilioTransporte;
    private LocalDate fechaIngreso;
    private Boolean activo;

    private NivelRiesgoArlDTO nivelRiesgoDTO;
    private TipoContratoDTO tipoContratoDTO;

    // Constructores
    public EmpleadoDTO() {
    }

    public EmpleadoDTO(String nombre, String cargo, String departamento, BigDecimal salarioBase, Boolean recibeAuxilioTransporte, LocalDate fechaIngreso, Boolean activo, NivelRiesgoArlDTO nivelRiesgoDTO, TipoContratoDTO tipoContratoDTO) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salarioBase = salarioBase;
        this.recibeAuxilioTransporte = recibeAuxilioTransporte;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.nivelRiesgoDTO = nivelRiesgoDTO;
        this.tipoContratoDTO = tipoContratoDTO;
    }

    public EmpleadoDTO(Integer idEmpleado, String nombre, String cargo, String departamento, BigDecimal salarioBase, Boolean recibeAuxilioTransporte, LocalDate fechaIngreso, Boolean activo, NivelRiesgoArlDTO nivelRiesgoDTO, TipoContratoDTO tipoContratoDTO) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salarioBase = salarioBase;
        this.recibeAuxilioTransporte = recibeAuxilioTransporte;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.nivelRiesgoDTO = nivelRiesgoDTO;
        this.tipoContratoDTO = tipoContratoDTO;
    }

    // Getters y setters


    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public NivelRiesgoArlDTO getNivelRiesgoDTO() {
        return nivelRiesgoDTO;
    }

    public void setNivelRiesgoDTO(NivelRiesgoArlDTO nivelRiesgoDTO) {
        this.nivelRiesgoDTO = nivelRiesgoDTO;
    }

    public TipoContratoDTO getTipoContratoDTO() {
        return tipoContratoDTO;
    }

    public void setTipoContratoDTO(TipoContratoDTO tipoContratoDTO) {
        this.tipoContratoDTO = tipoContratoDTO;
    }
}

