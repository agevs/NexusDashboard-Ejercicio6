/**
 * Widget informativo que muestra el clima de una ciudad.
 */
public class WidgetClima extends Widget {

    private String ciudad;
    private int temperatura;

    public WidgetClima(int id, String nombre, int memoriaKB,
                       String ciudad, int temperatura) {

        super(id, nombre, memoriaKB);

        this.ciudad = ciudad;
        this.temperatura = temperatura;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String renderizar() {
        return "Clima en " + ciudad + ": " + temperatura + " C";
    }

    @Override
    public String configuracion() {
        return super.configuracion()
                + "\nCiudad: " + ciudad
                + "\nTemperatura: " + temperatura + " C";
    }

    
    @Override
    public String getIcono() {
        return "☀";
    }

    @Override
    public String getCategoria() {
        return "Clima";
    }

}

