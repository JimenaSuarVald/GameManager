package Modelo;

public class Habilidades {
    private String nombre, efecto, descripcion;
    private int magnitud;

    public Habilidades(String nombre, String efecto, String descripcion, int magnitud) {
        this.nombre = nombre;
        this.efecto = efecto;
        this.descripcion = descripcion;
        this.magnitud = magnitud;
    }

    public String getEfecto() {
        return efecto;
    }

    public int getMagnitud() {
        return magnitud;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {

        return descripcion;
    }

}
