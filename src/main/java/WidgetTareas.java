
/**
 * Widget interactivo para gestionar tareas pendientes.
 * Implementa herencia, polimorfismo e interfaces.
 */
public class WidgetTareas extends Widget implements Interactivo {

    private int tareasPendientes;
    private final int tareasIniciales;

    public WidgetTareas(int id, String nombre,
                        int memoriaKB, int tareasPendientes) {

        super(id, nombre, memoriaKB);

        if (tareasPendientes < 0) {
            throw new IllegalArgumentException("Cantidad invalida");
        }

        this.tareasPendientes = tareasPendientes;
        this.tareasIniciales = tareasPendientes;
    }

    public int getTareasPendientes() {
        return tareasPendientes;
    }

    public int getTareasIniciales() {
        return tareasIniciales;
    }

    public double getProgreso() {
        if (tareasIniciales == 0) {
            return 1.0;
        }

        return (double) (tareasIniciales - tareasPendientes)
                / tareasIniciales;
    }

    @Override
    public String renderizar() {
        return "Tareas pendientes: " + tareasPendientes;
    }

    @Override
    public String interactuar() {
        if (tareasPendientes > 0) {
            tareasPendientes--;

            return "Tarea completada. Restantes: "
                    + tareasPendientes;
        }

        return "No quedan tareas pendientes.";
    }

    @Override
    public String configuracion() {
        return super.configuracion()
                + "\nTareas iniciales: " + tareasIniciales
                + "\nTareas pendientes: " + tareasPendientes
                + "\nProgreso: "
                + String.format("%.0f", getProgreso() * 100)
                + "%";
    }

    @Override
    public String getIcono() {
        return "☑";
    }

    @Override
    public String getCategoria() {
        return "Productividad";
    }
}
