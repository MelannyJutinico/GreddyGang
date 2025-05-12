package co.edu.unbosque.PayrollAPI.dto.regular;

import java.math.BigDecimal;

public class NivelRiesgoArlDTO {

    private Integer idNivel;
    private String nombre;
    private BigDecimal porcentaje;

    public NivelRiesgoArlDTO() {
    }

    public NivelRiesgoArlDTO(String nombre, BigDecimal porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public NivelRiesgoArlDTO(Integer idNivel, String nombre, BigDecimal porcentaje) {
        this.idNivel = idNivel;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }
}
