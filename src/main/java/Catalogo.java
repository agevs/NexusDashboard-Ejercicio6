import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalogo {

    private final List<Widget> widgets;

    public Catalogo() {
        widgets = new ArrayList<>();
    }

    /**
     * Agrega un widget al catalogo.
     * Acepta cualquier objeto que herede de Widget.
     */
    public void agregar(Widget widget) {

        if (widget == null) {
            throw new IllegalArgumentException(
                "No se puede agregar un widget nulo."
            );
        }

        if (buscar(widget.getId()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un widget con ese ID."
            );
        }

        widgets.add(widget);
    }

    /**
     * Overloading: busca un widget por ID.
     */
    public Widget buscar(int id) {

        for (Widget widget : widgets) {
            if (widget.getId() == id) {
                return widget;
            }
        }

        return null;
    }

    /**
     * Overloading: busca un widget por nombre.
     */
    public Widget buscar(String nombre) {

        if (nombre == null) {
            return null;
        }

        for (Widget widget : widgets) {
            if (widget.getNombre().equalsIgnoreCase(nombre.trim())) {
                return widget;
            }
        }

        return null;
    }

    /**
     * Devuelve una vista de la lista sin permitir
     * modificaciones externas.
     */
    public List<Widget> listar() {
        return Collections.unmodifiableList(widgets);
    }

    /**
     * Ordena los widgets de menor a mayor memoria.
     * Utiliza Comparable y compareTo().
     */
    public void ordenarPorMemoria() {
        Collections.sort(widgets);
    }

    /**
     * Carga inicial de 10 widgets diferentes.
     */
    public void cargarInicial() {

        agregar(new WidgetClima(
            1, "Clima Guatemala", 160, "Guatemala", 24
        ));

        agregar(new WidgetReloj(
            2, "Reloj principal", 60
        ));

        agregar(new WidgetTareas(
            3, "Mis tareas", 120, 5
        ));

        agregar(new WidgetAlerta(
            4, "Alerta servidor", 90, "Revisar conexion"
        ));

        agregar(new WidgetIndicador(
            5, "Ventas", 130, "Q 12,500"
        ));

        agregar(new WidgetClima(
            6, "Clima Antigua", 155, "Antigua Guatemala", 21
        ));

        agregar(new WidgetReloj(
            7, "Reloj secundario", 65
        ));

        agregar(new WidgetTareas(
            8, "Tareas equipo", 145, 8
        ));

        agregar(new WidgetAlerta(
            9, "Alerta respaldo", 100, "Respaldo pendiente"
        ));

        agregar(new WidgetIndicador(
            10, "Usuarios activos", 75, "243"
        ));
    }
}