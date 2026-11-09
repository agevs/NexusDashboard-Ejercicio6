import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * CC2008 - Ejercicio 6
 * Punto de entrada de NEXUS Dashboard.
 */
public class Principal extends Application {

    @Override
    public void start(Stage escenario) {
        Application.setUserAgentStylesheet(
            new PrimerDark().getUserAgentStylesheet()
        );

        Catalogo catalogo = new Catalogo();
        catalogo.cargarInicial();

        VistaDashboard vista = new VistaDashboard();

        new ControladorDashboard(catalogo, vista);

        Scene escena = new Scene(vista.getRaiz(), 1280, 800);

        String css = getClass()
            .getResource("/styles/dashboard.css")
            .toExternalForm();

        escena.getStylesheets().add(css);

        escenario.setTitle("NEXUS | Dashboard Empresarial");
        escenario.setScene(escena);
        escenario.setMinWidth(1000);
        escenario.setMinHeight(680);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}