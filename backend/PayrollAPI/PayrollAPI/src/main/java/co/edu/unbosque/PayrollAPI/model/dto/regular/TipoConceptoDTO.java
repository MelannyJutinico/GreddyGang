package co.edu.unbosque.PayrollAPI.model.dto.regular;

public class TipoConceptoDTO {

    private Integer idTipoConcepto;
    private String nombre;

    public TipoConceptoDTO() {
    }

    public TipoConceptoDTO(String nombre) {
        this.nombre = nombre;
    }

    public TipoConceptoDTO(Integer idTipoConcepto, String nombre) {
        this.idTipoConcepto = idTipoConcepto;
        this.nombre = nombre;
    }

    public Integer getIdTipoConcepto() {
        return idTipoConcepto;
    }

    public void setIdTipoConcepto(Integer idTipoConcepto) {
        this.idTipoConcepto = idTipoConcepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
