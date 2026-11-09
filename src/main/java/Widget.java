import java.util.Objects;

/**
 * Clase abstracta que representa un widget del Dashboard.
 * Implementa Renderizable y Comparable para permitir
 * polimorfismo y ordenamiento por memoria.
 */
public abstract class Widget implements Renderizable, Personalizable, Comparable<Widget> {
    private final int id;
    private String nombre;
    private int memoriaKB;

    public Widget(int id, String nombre, int memoriaKB) {
        if (id <= 0 || nombre == null || nombre.isBlank() || memoriaKB < 0) {
            throw new IllegalArgumentException("Datos invalidos");
        }

        this.id = id;
        this.nombre = nombre;
        this.memoriaKB = memoriaKB;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMemoriaKB() {
        return memoriaKB;
    }

    // Setters
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre invalido");
        }

        this.nombre = nombre;
    }

    public void setMemoriaKB(int memoriaKB) {
        if (memoriaKB < 0) {
            throw new IllegalArgumentException("Memoria invalida");
        }

        this.memoriaKB = memoriaKB;
    }

    // Muestra la configuracion del widget
    public String configuracion() {
        return "ID: " + id
                + "\nNombre: " + nombre
                + "\nTipo: " + getClass().getSimpleName()
                + "\nMemoria: " + memoriaKB + " KB"
                + "\nInteractivo: "
                + (this instanceof Interactivo ? "Si" : "No");
    }

    // Ordenamiento por consumo de memoria
    @Override
    public int compareTo(Widget otro) {
        int resultado = Integer.compare(this.memoriaKB, otro.memoriaKB);

        if (resultado != 0) {
            return resultado;
        }

        return Integer.compare(this.id, otro.id);
    }

    // Dos widgets son iguales cuando tienen el mismo ID
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Widget)) {
            return false;
        }

        Widget otro = (Widget) objeto;
        return this.id == otro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nombre + " (" + memoriaKB + " KB)";
    }

    @Override
    public String getIcono() {
        return "◆";
    }

    @Override
    public String getCategoria() {
        return "General";
    }

}
