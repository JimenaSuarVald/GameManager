package modelo;

public class Jugador extends Persona {

    private int puntuacionTotal;
    private int record;
    private int partidasJugadas;

    public Jugador(String nombre, String contraseña) {

        super(nombre, contraseña);

        puntuacionTotal = 0;
        record = 0;
        partidasJugadas = 0;
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public int getRecord() {
        return record;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void sumarPuntos(int puntos) {

        puntuacionTotal += puntos;

        if (puntos > record) {
            record = puntos;
        }
    }
}