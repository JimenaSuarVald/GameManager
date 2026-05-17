package modelo;

public class Jugador extends Persona {
    
    // Atributos específicos del jugador (para las estadísticas del grupo)
    private int contadorAccesos;
    private int partidasJugadas;
    private int puntuacionMaxima;

    // Este ees el constructor de jugador
    public Jugador(String nombre, String contraseña) {
        /*'super' llama obligatoriamente al constructor de la clase madre (Persona)
         y le pasa el nombre y la contraseña para que ella gestione el ID automático.*/
        super(nombre, contraseña);
        
        // Inicializamos los atributos del jugador a cero
        this.contadorAccesos = 0;
        this.partidasJugadas = 0;
        this.puntuacionMaxima = 0;
    }

    // Métodos para controlar los accesos (lo que pedia Crisenti)
    public int getContadorAccesos() {
        return contadorAccesos;
    }

    public void incrementarAccesos() {
        this.contadorAccesos++;
    }

    // Métodos para las estadísticas de los juegos ( Dani y Jimena creo q os tocaba profundizar aquí)
    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void incrementarPartidas() {
        this.partidasJugadas++;
    }

    public int getPuntuacionMaxima() {
        return puntuacionMaxima;
    }

    public void setPuntuacionMaxima(int puntuacionMaxima) {
        this.puntuacionMaxima = puntuacionMaxima;
    }
}