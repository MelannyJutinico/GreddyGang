package co.edu.unbosque.PayrollAPI.model.dto.regular;

import java.math.BigDecimal;

public class ConceptoNominaDTO {

    private Integer idConcepto;
    private String nombre;
    private Integer idTipoConcepto; // 👈 agregar esto
    private String tipoConcepto;    // 👈 el nombre del tipo (ej. "Deducción")
    private String descripcion;
    private Boolean registroManual;
    private BigDecimal porcentaje;
    private Boolean activo;


    public ConceptoNominaDTO() {
    }

    public ConceptoNominaDTO(Integer idConcepto, String nombre, Integer idTipoConcepto, String tipoConcepto, String descripcion, Boolean registroManual, BigDecimal porcentaje, Boolean activo) {
        this.idConcepto = idConcepto;
        this.nombre = nombre;
        this.idTipoConcepto = idTipoConcepto;
        this.tipoConcepto = tipoConcepto;
        this.descripcion = descripcion;
        this.registroManual = registroManual;
        this.porcentaje = porcentaje;
        this.activo = activo;
    }

    public Integer getIdTipoConcepto() {
        return idTipoConcepto;
    }

    public void setIdTipoConcepto(Integer idTipoConcepto) {
        this.idTipoConcepto = idTipoConcepto;
    }

    public String getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(String tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Boolean getRegistroManual() {
        return registroManual;
    }

    public void setRegistroManual(Boolean registroManual) {
        this.registroManual = registroManual;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
