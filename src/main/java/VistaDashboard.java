
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Vista del patron MVC.
 * Dashboard grafico con JavaFX y AtlantaFX.
 */
public class VistaDashboard {

    private final BorderPane raiz = new BorderPane();
    private final FlowPane panelWidgets = new FlowPane(16, 16);
    private final VBox panelDetalles = new VBox(15);

    private final TextField campoBuscar = new TextField();

    private final Button botonBuscar = new Button("Buscar");
    private final Button botonTodos = new Button("Todos");
    private final Button botonOrdenar = new Button("Ordenar por memoria");
    private final Button botonInteractuar = new Button("Ejecutar accion");

    private final Label tituloDetalles = new Label("Selecciona un widget");
    private final Label contenidoDetalles = new Label(
        "Haz clic en una tarjeta para ver su configuracion."
    );

    private final Label estado = new Label("Sistema listo");
    private final Label contador = new Label("0");
    private final Label memoria = new Label("0 KB");
    private final Label interactivos = new Label("0");

    private final Button menuInicio = new Button("Dashboard");
    private final Button menuWidgets = new Button("Todos los widgets");
    private final Button menuMemoria = new Button("Rendimiento");

    // Referencias a los componentes visuales.
    // No es otra lista de widgets.
    private final Map<Widget, Label> contenidos =
        new IdentityHashMap<>();

    public VistaDashboard() {
        construirInterfaz();
    }

    // GETTERS

    public BorderPane getRaiz() {
        return raiz;
    }

    public FlowPane getPanelWidgets() {
        return panelWidgets;
    }

    public TextField getCampoBuscar() {
        return campoBuscar;
    }

    public Button getBotonBuscar() {
        return botonBuscar;
    }

    public Button getBotonTodos() {
        return botonTodos;
    }

    public Button getBotonOrdenar() {
        return botonOrdenar;
    }

    public Button getBotonInteractuar() {
        return botonInteractuar;
    }

    public Button getMenuInicio() {
        return menuInicio;
    }

    public Button getMenuWidgets() {
        return menuWidgets;
    }

    public Button getMenuMemoria() {
        return menuMemoria;
    }

    public void setEstado(String mensaje) {
        estado.setText(mensaje);
    }

    public void actualizarResumen(int total, int totalKB, int activos) {
        contador.setText(String.valueOf(total));
        memoria.setText(totalKB + " KB");
        interactivos.setText(String.valueOf(activos));
    }

    // CONSTRUCCION DE LA INTERFAZ

    private void construirInterfaz() {
        raiz.getStyleClass().add("dashboard-root");

        // MENU LATERAL
        VBox lateral = new VBox(16);
        lateral.getStyleClass().add("sidebar");
        lateral.setPrefWidth(225);

        Label logo = new Label("◆ NEXUS");
        logo.getStyleClass().add("logo");

        Label subtitulo = new Label("WORKSPACE");
        subtitulo.getStyleClass().add("sidebar-subtitle");

        for (Button boton : new Button[]{
                menuInicio, menuWidgets, menuMemoria}) {
            boton.getStyleClass().add("nav-button");
            boton.setMaxWidth(Double.MAX_VALUE);
        }

        Label version = new Label("NEXUS 1.0 | JavaFX");
        version.getStyleClass().add("muted");

        Region espacio = new Region();
        VBox.setVgrow(espacio, Priority.ALWAYS);

        lateral.getChildren().addAll(
            logo, subtitulo, menuInicio,
            menuWidgets, menuMemoria, espacio, version
        );

        raiz.setLeft(lateral);

        // ENCABEZADO
        Label titulo = new Label("Dashboard Overview");
        titulo.getStyleClass().add("page-title");

        Label descripcion = new Label(
            "Gestiona y supervisa tus widgets empresariales"
        );
        descripcion.getStyleClass().add("muted");

        VBox encabezado = new VBox(5, titulo, descripcion);

        // TARJETAS DE RESUMEN
        HBox resumen = new HBox(15,
            tarjetaResumen("WIDGETS ACTIVOS", contador, "◆"),
            tarjetaResumen("MEMORIA TOTAL", memoria, "◈"),
            tarjetaResumen("INTERACTIVOS", interactivos, "✦")
        );

        // BARRA DE BUSQUEDA
        campoBuscar.setPromptText("Buscar por ID o nombre...");
        campoBuscar.setPrefWidth(280);

        botonBuscar.getStyleClass().add("primary-button");

        HBox herramientas = new HBox(
            10, campoBuscar, botonBuscar,
            botonTodos, botonOrdenar
        );
        herramientas.setAlignment(Pos.CENTER_LEFT);
        herramientas.getStyleClass().add("toolbar");

        // CONTENEDOR DE WIDGETS
        panelWidgets.setPadding(new Insets(5));
        panelWidgets.setAlignment(Pos.TOP_LEFT);

        ScrollPane scrollWidgets = new ScrollPane(panelWidgets);
        scrollWidgets.setFitToWidth(true);
        scrollWidgets.getStyleClass().add("widgets-scroll");
        VBox.setVgrow(scrollWidgets, Priority.ALWAYS);

        // PANEL DE DETALLES
        panelDetalles.getStyleClass().add("details-panel");
        panelDetalles.setPrefWidth(285);
        panelDetalles.setMinWidth(250);

        Label encabezadoDetalles = new Label("CONFIGURACION");
        encabezadoDetalles.getStyleClass().add("section-label");

        tituloDetalles.getStyleClass().add("detail-title");

        contenidoDetalles.setWrapText(true);
        contenidoDetalles.getStyleClass().add("detail-content");

        botonInteractuar.getStyleClass().add("primary-button");
        botonInteractuar.setMaxWidth(Double.MAX_VALUE);
        botonInteractuar.setDisable(true);

        Region separador = new Region();
        VBox.setVgrow(separador, Priority.ALWAYS);

        panelDetalles.getChildren().addAll(
            encabezadoDetalles, tituloDetalles,
            contenidoDetalles, separador, botonInteractuar
        );

        // AREA CENTRAL
        HBox areaCentral = new HBox(18, scrollWidgets, panelDetalles);
        HBox.setHgrow(scrollWidgets, Priority.ALWAYS);
        VBox.setVgrow(areaCentral, Priority.ALWAYS);

        // BARRA DE ESTADO
        estado.getStyleClass().add("status-label");

        // CONTENIDO PRINCIPAL
        VBox contenido = new VBox(
            20, encabezado, resumen,
            herramientas, areaCentral, estado
        );
        contenido.getStyleClass().add("main-content");
        VBox.setVgrow(areaCentral, Priority.ALWAYS);

        raiz.setCenter(contenido);
    }

    // TARJETAS DE RESUMEN

    private VBox tarjetaResumen(String texto, Label valor, String simbolo) {
        Label icono = new Label(simbolo);
        icono.getStyleClass().add("summary-icon");

        Label etiqueta = new Label(texto);
        etiqueta.getStyleClass().add("section-label");

        valor.getStyleClass().add("summary-value");

        VBox tarjeta = new VBox(10, icono, etiqueta, valor);
        tarjeta.getStyleClass().add("summary-card");

        HBox.setHgrow(tarjeta, Priority.ALWAYS);
        tarjeta.setMaxWidth(Double.MAX_VALUE);

        return tarjeta;
    }

    // TARJETAS POLIMORFICAS

    public VBox crearTarjeta(Widget widget, Runnable alSeleccionar) {

        Personalizable apariencia = widget;
        Renderizable renderizable = widget;

        Label icono = new Label(apariencia.getIcono());
        icono.getStyleClass().add("widget-icon");

        Label nombre = new Label(widget.getNombre());
        nombre.getStyleClass().add("widget-title");

        Label tipo = new Label(apariencia.getCategoria());
        tipo.getStyleClass().add("muted");

        Label contenido = new Label(renderizable.renderizar());
        contenido.getStyleClass().add("widget-content");
        contenido.setWrapText(true);
        contenido.setTextAlignment(TextAlignment.LEFT);

        contenidos.put(widget, contenido);

        Label memoriaWidget = new Label(
            widget.getMemoriaKB() + " KB"
        );
        memoriaWidget.getStyleClass().add("memory-label");

        Label estadoWidget = new Label(
            widget instanceof Interactivo
                ? "● Interactivo" : "● Informativo"
        );
        estadoWidget.getStyleClass().add("widget-status");

        HBox pie = new HBox(10, memoriaWidget, estadoWidget);
        pie.setAlignment(Pos.CENTER_LEFT);

        VBox tarjeta = new VBox(
            12, icono, nombre, tipo, contenido
        );

        // PROGRESO VISUAL DE TAREAS
        if (widget instanceof WidgetTareas) {
            WidgetTareas tareas = (WidgetTareas) widget;

            ProgressBar barra = new ProgressBar(tareas.getProgreso());
            barra.setMaxWidth(Double.MAX_VALUE);
            barra.getStyleClass().add("task-progress");

            tarjeta.getChildren().add(barra);

            Label porcentaje = new Label(
                String.format("%.0f%% completado",
                    tareas.getProgreso() * 100)
            );
            porcentaje.getStyleClass().add("muted");
            tarjeta.getChildren().add(porcentaje);
        }

        // ESTADOS VISUALES DE ALERTAS
        if (widget instanceof WidgetAlerta) {
            WidgetAlerta alerta = (WidgetAlerta) widget;

            if (alerta.isActiva()) {
                estadoWidget.setText("● Alerta activa");
                estadoWidget.getStyleClass().add("alert-active");
            } else {
                estadoWidget.setText("● Atendida");
                estadoWidget.getStyleClass().add("alert-resolved");
            }
        }

        tarjeta.getChildren().add(pie);

        tarjeta.getStyleClass().add("widget-card");
        tarjeta.setPrefWidth(245);
        tarjeta.setMinHeight(230);

        tarjeta.setOnMouseClicked(e -> alSeleccionar.run());

        return tarjeta;
    }

    // ACTUALIZACION DE RELOJES

    public void actualizarRelojes() {
        for (Map.Entry<Widget, Label> entrada : contenidos.entrySet()) {
            Widget widget = entrada.getKey();

            if (widget instanceof WidgetReloj) {
                entrada.getValue().setText(widget.renderizar());
            }
        }
    }

    public void limpiarReferenciasVisuales() {
        contenidos.clear();
    }

    // PANEL DE DETALLES

    public void mostrarDetalles(Widget widget) {
        tituloDetalles.setText(widget.getNombre());

        contenidoDetalles.setText(
            widget.configuracion()
            + "\n\nCONTENIDO ACTUAL\n"
            + widget.renderizar()
        );

        botonInteractuar.setDisable(
            !(widget instanceof Interactivo)
        );
    }

    public void limpiarDetalles() {
        tituloDetalles.setText("Selecciona un widget");
        contenidoDetalles.setText(
            "Haz clic en una tarjeta para ver su configuracion."
        );
        botonInteractuar.setDisable(true);
    }
}
