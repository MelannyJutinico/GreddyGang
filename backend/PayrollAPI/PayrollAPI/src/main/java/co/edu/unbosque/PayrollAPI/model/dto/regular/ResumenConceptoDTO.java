package co.edu.unbosque.PayrollAPI.model.dto.regular;

import java.math.BigDecimal;

public class ResumenConceptoDTO {
    private String tipo;
    private String nombre;
    private BigDecimal valor;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}

