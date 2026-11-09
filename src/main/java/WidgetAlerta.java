/**
 * Widget interactivo que administra notificaciones.
 */
public class WidgetAlerta extends Widget implements Interactivo {

    private String mensaje;
    private boolean activa;

    public WidgetAlerta(int id, String nombre,
                        int memoriaKB, String mensaje) {

        super(id, nombre, memoriaKB);

        this.mensaje = mensaje;
        this.activa = true;
    }

    public boolean isActiva() {
        return activa;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String renderizar() {
        if (activa) {
            return "ALERTA: " + mensaje;
        }

        return "Alerta atendida";
    }

    @Override
    public String interactuar() {
        if (activa) {
            activa = false;
            return "Alerta marcada como atendida.";
        }

        return "Esta alerta ya fue atendida.";
    }

    @Override
    public String configuracion() {
        return super.configuracion()
                + "\nMensaje: " + mensaje
                + "\nEstado: " + (activa ? "Activa" : "Atendida");
    }
 
    @Override
    public String getIcono() {
        return "⚠";
    }

    @Override
    public String getCategoria() {
        return "Notificaciones";
    }

}

