package modelo;

public class Jugador extends Persona {

    private int puntuacionTotal;
    private int record;
    private int partidasJugadas;
    private int victorias;

    public Jugador(String nombre, String contraseña) {
        super(nombre, contraseña);
        this.puntuacionTotal = 0;
        this.record = 0;
        this.partidasJugadas = 0;
        this.victorias = 0;
    }

    public void registrarPartida(int puntos, boolean victoria) {
        partidasJugadas++;
        puntuacionTotal += puntos;

        if (puntos > record) {
            record = puntos;
        }

        if (victoria) {
            victorias++;
        }
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

    public int getVictorias() {
        return victorias;
    }
}