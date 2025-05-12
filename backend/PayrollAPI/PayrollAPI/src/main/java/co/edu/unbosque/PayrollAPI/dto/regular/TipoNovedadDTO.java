package co.edu.unbosque.PayrollAPI.dto;

public class TipoNovedadDTO {

    private Integer idTipoNovedad;

    private String nombre;

    private String descripcion;

    private Boolean activo;

    public TipoNovedadDTO() {
    }

    public TipoNovedadDTO(String nombre, String descripcion, Boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public TipoNovedadDTO(Integer idTipoNovedad, String nombre, String descripcion, Boolean activo) {
        this.idTipoNovedad = idTipoNovedad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Integer getIdTipoNovedad() {
        return idTipoNovedad;
    }

    public void setIdTipoNovedad(Integer idTipoNovedad) {
        this.idTipoNovedad = idTipoNovedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
