package ModeloGen;

public class Jugador extends Persona {
    private int puntuacionTotal;
    private int record;
    private int partidasJugadas;
    private int victorias;

    public Jugador(String nombre, String contraseña) {
        super(nombre, contraseña);
    }

    public void registrarPartida(int puntos, boolean victoria) {
        partidasJugadas++;
        puntuacionTotal += puntos;
        if (puntos > record) record = puntos;
        if (victoria) victorias++;
    }

    public int getPuntuacionTotal() { return puntuacionTotal; }
    public void setPuntuacionTotal(int p) { this.puntuacionTotal = p; }
    public int getRecord() { return record; }
    public void setRecord(int r) { this.record = r; }
    public int getPartidasJugadas() { return partidasJugadas; }
    public void setPartidasJugadas(int p) { this.partidasJugadas = p; }
    public int getVictorias() { return victorias; }
    public void setVictorias(int v) { this.victorias = v; }
}