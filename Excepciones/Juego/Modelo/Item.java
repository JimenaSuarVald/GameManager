package Modelo;

public class Item {
    private String nombre, efecto, descripcion;
    int magnitud = 0;

    public  Item(String nombre, String efecto, int magnitud, String descripcion) {
        this.nombre = nombre;
        this.efecto = efecto;
        this.magnitud = magnitud;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }
    public String getEfecto() {
        return efecto;
    }
    public int getMagnitud() {
        return magnitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
