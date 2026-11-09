/**
 * Widget informativo que muestra indicadores empresariales.
 */
public class WidgetIndicador extends Widget {

    private String valor;

    public WidgetIndicador(int id, String nombre,
                           int memoriaKB, String valor) {

        super(id, nombre, memoriaKB);
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String renderizar() {
        return getNombre() + ": " + valor;
    }

    @Override
    public String configuracion() {
        return super.configuracion()
                + "\nValor actual: " + valor;
    }
    
    @Override
    public String getIcono() {
        return "◈";
    }

    @Override
    public String getCategoria() {
        return "Estadisticas";
    }

}


