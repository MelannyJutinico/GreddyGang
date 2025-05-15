package co.edu.unbosque.PayrollAPI.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleado")
@NamedStoredProcedureQuery(
        name = "Empleado.crearEmpleado",
        procedureName = "sp_crear_empleado",
        parameters = {
                @StoredProcedureParameter(name = "pn_nombre", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "pn_cargo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "pn_departamento", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "pn_salario_base", type = BigDecimal.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "pd_fecha_ingreso", type = LocalDate.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "pb_activo", type = Boolean.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "pn_nivel_riesgo", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "pn_id_tipo_contrato", type = Integer.class, mode = ParameterMode.IN)
        }
)
public class Empleado {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "salario_base")
    private BigDecimal salarioBase;

    @Column(name = "recibe_auxilio_transporte")
    private Boolean recibeAuxilioTransporte;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "nivel_riesgo")
    private NivelRiesgoArl nivelRiesgo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_contrato")
    private TipoContrato tipoContrato;

    //Constructores

    public Empleado() {
    }

    public Empleado(String nombre, String cargo, String departamento, BigDecimal salarioBase, Boolean recibeAuxilioTransporte, LocalDate fechaIngreso, Boolean activo, NivelRiesgoArl nivelRiesgo, TipoContrato tipoContrato) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salarioBase = salarioBase;
        this.recibeAuxilioTransporte = recibeAuxilioTransporte;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.nivelRiesgo = nivelRiesgo;
        this.tipoContrato = tipoContrato;
    }

    public Empleado(Integer idEmpleado, String nombre, String cargo, String departamento, BigDecimal salarioBase, Boolean recibeAuxilioTransporte, LocalDate fechaIngreso, Boolean activo, NivelRiesgoArl nivelRiesgo, TipoContrato tipoContrato) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salarioBase = salarioBase;
        this.recibeAuxilioTransporte = recibeAuxilioTransporte;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.nivelRiesgo = nivelRiesgo;
        this.tipoContrato = tipoContrato;
    }



    //Getters & Setters


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

    public NivelRiesgoArl getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(NivelRiesgoArl nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
}

