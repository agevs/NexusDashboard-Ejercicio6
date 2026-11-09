import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Widget interactivo que muestra la hora actual.
 */
public class WidgetReloj extends Widget implements Interactivo {

    private boolean formato24h;

    public WidgetReloj(int id, String nombre, int memoriaKB) {
        super(id, nombre, memoriaKB);
        this.formato24h = true;
    }

    public boolean isFormato24h() {
        return formato24h;
    }

    @Override
    public String renderizar() {
        DateTimeFormatter formato;

        if (formato24h) {
            formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        } else {
            formato = DateTimeFormatter.ofPattern("hh:mm:ss a");
        }

        return "Hora actual: " + LocalTime.now().format(formato);
    }

    @Override
    public String interactuar() {
        formato24h = !formato24h;
        return "Formato del reloj cambiado correctamente.";
    }

    @Override
    public String configuracion() {
        return super.configuracion()
                + "\nFormato: " + (formato24h ? "24 horas" : "12 horas");
    }

    
    @Override
    public String getIcono() {
        return "◷";
    }

    @Override
    public String getCategoria() {
        return "Tiempo";
    }

}

