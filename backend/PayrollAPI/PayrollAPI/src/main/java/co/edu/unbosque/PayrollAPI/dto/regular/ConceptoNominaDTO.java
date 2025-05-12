package co.edu.unbosque.PayrollAPI.dto.regular;

import java.math.BigDecimal;

public class ConceptoNominaDTO {

    private Integer idConcepto;

    private String nombre;

    private TipoConceptoDTO tipoConceptoDTO;

    private Boolean registroManual;

    private BigDecimal porcentaje;

    private String descripcion;

    private Boolean activo;

    public ConceptoNominaDTO() {
    }

    public ConceptoNominaDTO(String nombre, TipoConceptoDTO tipoConceptoDTO, Boolean registroManual, BigDecimal porcentaje, String descripcion, Boolean activo) {
        this.nombre = nombre;
        this.tipoConceptoDTO = tipoConceptoDTO;
        this.registroManual = registroManual;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public ConceptoNominaDTO(Integer idConcepto, String nombre, TipoConceptoDTO tipoConceptoDTO, Boolean registroManual, BigDecimal porcentaje, String descripcion, Boolean activo) {
        this.idConcepto = idConcepto;
        this.nombre = nombre;
        this.tipoConceptoDTO = tipoConceptoDTO;
        this.registroManual = registroManual;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
        this.activo = activo;
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

    public TipoConceptoDTO getTipoConceptoDTO() {
        return tipoConceptoDTO;
    }

    public void setTipoConceptoDTO(TipoConceptoDTO tipoConceptoDTO) {
        this.tipoConceptoDTO = tipoConceptoDTO;
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
