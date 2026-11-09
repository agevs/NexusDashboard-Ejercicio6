/**
 * Interfaz para personalizar la apariencia de un widget.
 *
 * Permite agregar nuevos tipos de widgets sin
 * modificar el codigo de la vista principal.
 */
public interface Personalizable {

    String getIcono();

    String getCategoria();
}
