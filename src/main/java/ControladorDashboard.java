
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Controlador del patron MVC.
 * Coordina el modelo y la vista.
 */
public class ControladorDashboard {

    private final Catalogo catalogo;
    private final VistaDashboard vista;
    private Widget seleccionado;
    private final Timeline actualizador;

    public ControladorDashboard(
            Catalogo catalogo, VistaDashboard vista) {

        this.catalogo = catalogo;
        this.vista = vista;

        vista.getBotonBuscar().setOnAction(e -> buscar());
        vista.getCampoBuscar().setOnAction(e -> buscar());

        vista.getBotonTodos().setOnAction(e -> mostrarTodos());
        vista.getMenuInicio().setOnAction(e -> mostrarTodos());
        vista.getMenuWidgets().setOnAction(e -> mostrarTodos());

        vista.getBotonOrdenar().setOnAction(e -> ordenar());
        vista.getMenuMemoria().setOnAction(e -> ordenar());

        vista.getBotonInteractuar().setOnAction(e -> interactuar());

        mostrarTodos();

        // Actualizacion automatica de los relojes.
        actualizador = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                vista.actualizarRelojes();

                if (seleccionado instanceof WidgetReloj) {
                    vista.mostrarDetalles(seleccionado);
                }
            })
        );

        actualizador.setCycleCount(Timeline.INDEFINITE);
        actualizador.play();
    }

    private void mostrarTodos() {
        seleccionado = null;
        vista.getCampoBuscar().clear();
        vista.limpiarDetalles();

        mostrarWidgets();
        vista.setEstado("Mostrando todos los widgets.");
    }

    private void mostrarWidgets() {
        vista.limpiarReferenciasVisuales();
        vista.getPanelWidgets().getChildren().clear();

        for (Widget widget : catalogo.listar()) {
            vista.getPanelWidgets().getChildren().add(
                vista.crearTarjeta(
                    widget, () -> seleccionar(widget)
                )
            );
        }

        actualizarResumen();
    }

    private void actualizarResumen() {
        int memoriaTotal = 0;
        int totalInteractivos = 0;

        for (Widget widget : catalogo.listar()) {
            memoriaTotal += widget.getMemoriaKB();

            if (widget instanceof Interactivo) {
                totalInteractivos++;
            }
        }

        vista.actualizarResumen(
            catalogo.listar().size(),
            memoriaTotal,
            totalInteractivos
        );
    }

    private void seleccionar(Widget widget) {
        seleccionado = widget;
        vista.mostrarDetalles(widget);
        vista.setEstado("Seleccionado: " + widget.getNombre());
    }

    private void buscar() {
        String texto = vista.getCampoBuscar().getText().trim();

        if (texto.isEmpty()) {
            vista.setEstado("Escribe un ID o nombre.");
            return;
        }

        Widget resultado;

        try {
            resultado = catalogo.buscar(Integer.parseInt(texto));
        } catch (NumberFormatException ex) {
            resultado = catalogo.buscar(texto);
        }

        vista.limpiarReferenciasVisuales();
        vista.getPanelWidgets().getChildren().clear();

        if (resultado == null) {
            seleccionado = null;
            vista.limpiarDetalles();
            vista.setEstado("No se encontro: " + texto);
            return;
        }

        Widget encontrado = resultado;

        vista.getPanelWidgets().getChildren().add(
            vista.crearTarjeta(
                encontrado, () -> seleccionar(encontrado)
            )
        );

        seleccionar(resultado);
        vista.setEstado("Widget encontrado correctamente.");
    }

    private void ordenar() {
        catalogo.ordenarPorMemoria();
        seleccionado = null;
        vista.limpiarDetalles();
        mostrarWidgets();
        vista.setEstado("Ordenados por memoria de menor a mayor.");
    }

    private void interactuar() {
        if (!(seleccionado instanceof Interactivo)) {
            return;
        }

        Interactivo elemento = (Interactivo) seleccionado;
        String respuesta = ejecutarInteraccion(elemento);

        Widget actual = seleccionado;

        // Actualiza las tarjetas y sus indicadores visuales.
        vista.limpiarReferenciasVisuales();
        vista.getPanelWidgets().getChildren().clear();

        for (Widget widget : catalogo.listar()) {
            vista.getPanelWidgets().getChildren().add(
                vista.crearTarjeta(
                    widget, () -> seleccionar(widget)
                )
            );
        }

        seleccionado = actual;
        vista.mostrarDetalles(actual);
        vista.setEstado(respuesta);
    }

    private String ejecutarInteraccion(Interactivo elemento) {
        return elemento.interactuar();
    }
}
