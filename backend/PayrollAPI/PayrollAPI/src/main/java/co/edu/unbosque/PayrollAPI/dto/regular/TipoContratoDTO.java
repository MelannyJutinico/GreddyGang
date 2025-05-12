package co.edu.unbosque.PayrollAPI.dto;

public class TipoContratoDTO {

    private Integer idTipoContrato;
    private String nombre;

    public TipoContratoDTO() {
    }

    public TipoContratoDTO(String nombre) {
        this.nombre = nombre;
    }

    public TipoContratoDTO(Integer idTipoContrato, String nombre) {
        this.idTipoContrato = idTipoContrato;
        this.nombre = nombre;
    }

    public Integer getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(Integer idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
