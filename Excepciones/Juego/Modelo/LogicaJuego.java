package Modelo;

public class LogicaJuego {
    private Usuario usuario;
    private Jugador jugador;
    private int capituloActual;

    public LogicaJuego(Usuario usuario, Jugador jugador, int capituloActual) {
        this.usuario = usuario;
        this.jugador = jugador;
        this.capituloActual = capituloActual;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getCapituloActual() {
        return capituloActual;
    }

    public void setCapituloActual(int capituloActual) {
        this.capituloActual = capituloActual;
    }
}