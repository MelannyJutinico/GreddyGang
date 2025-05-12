package co.edu.unbosque.PayrollAPI.dto.regular;

public class MensajeDTO {

    private String estado;
    private String mensaje;

    public MensajeDTO() {
    }

    public MensajeDTO(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "MensajeDTO{" +
                "estado='" + estado + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
