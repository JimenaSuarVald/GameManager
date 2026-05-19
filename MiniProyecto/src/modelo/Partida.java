package modelo;

public class Partida {

    private String nombreJugador;
    private String juego;
    private int puntuacion;
    private boolean victoria;

    public Partida(String nombreJugador, String juego, int puntuacion, boolean victoria) {
        this.nombreJugador = nombreJugador;
        this.juego = juego;
        this.puntuacion = puntuacion;
        this.victoria = victoria;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public String getJuego() {
        return juego;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public boolean isVictoria() {
        return victoria;
    }
}