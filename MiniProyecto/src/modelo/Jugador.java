package modelo;

import java.util.ArrayList;

public class Jugador extends Persona {

    // Atributos especificos del jugador (estadisticas)
    private int contadorAccesos;
    private int partidasJugadas;
    private int puntuacionMaxima;

    // Historial de partidas para que el admin pueda verlas (lo pide el enunciado)
    private ArrayList<String> historialPartidas;

    // Constructor del jugador
    public Jugador(String nombre, String contraseña) {
        super(nombre, contraseña);

        this.contadorAccesos = 0;
        this.partidasJugadas = 0;
        this.puntuacionMaxima = 0;
        this.historialPartidas = new ArrayList<>();
    }

    // Metodos para controlar los accesos
    public int getContadorAccesos() {
        return contadorAccesos;
    }

    public void incrementarAccesos() {
        this.contadorAccesos++;
    }

    public void setContadorAccesos(int contadorAccesos) {
        this.contadorAccesos = contadorAccesos;
    }

    // Metodos para las estadisticas de partidas
    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void incrementarPartidas() {
        this.partidasJugadas++;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public int getPuntuacionMaxima() {
        return puntuacionMaxima;
    }

    public void setPuntuacionMaxima(int puntuacionMaxima) {
      //If nueva puntuacion es mayor
        if (puntuacionMaxima > this.puntuacionMaxima) {
            this.puntuacionMaxima = puntuacionMaxima;
        }
    }

  
    public ArrayList<String> getHistorialPartidas() {
        return historialPartidas;
    }

   //Para el txt 
    public void añadirPartidaAlHistorial(String juego, String fecha, int puntuacion) {
        String entrada = juego + " | " + fecha + " | Puntuacion: " + puntuacion;
        historialPartidas.add(entrada);
    }
}