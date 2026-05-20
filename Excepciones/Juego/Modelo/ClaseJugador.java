package Modelo;

public class ClaseJugador {
    private String nombre, tipoDaño;
    private String[] Habilidades;

    public ClaseJugador(String nombre, String tipoDaño, String[] Habilidades) {
        this.nombre = nombre;
        this.tipoDaño = tipoDaño;
        this.Habilidades = Habilidades;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoDaño() {
        return tipoDaño;
    }

    public String[] getHabilidades() {
        return Habilidades;
    }
}