package Modelo;

public class Jugador {

    private String nombre;
    private int vida;
    private int defensa;
    private String rutaImagen;
    private Item arma;
    private Mochila mochila;
    private ClaseJugador claseJugador;
    private Usuario usuario;
    private int turnosExtra;
    private int dañoVeneno;

    public Jugador(Usuario usuario, ClaseJugador claseJugador, String nombre,
                   int vida, Item arma, Mochila mochila) {
        this.usuario = usuario;
        this.claseJugador = claseJugador;
        this.nombre = nombre;
        this.vida = vida;
        this.defensa = 0;
        this.arma = arma;
        this.mochila = mochila;
        this.turnosExtra = 0;
        this.dañoVeneno = 0;
    }

    public String getNombre() { return nombre; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }

    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) { this.defensa = defensa; }

    public Item getArma() { return arma; }
    public void setArma(Item arma) { this.arma = arma; }

    public ClaseJugador getClaseJugador() { return claseJugador; }
    public Usuario getUsuario() { return usuario; }

    public int getTurnosExtra() { return turnosExtra; }
    public void setTurnosExtra(int turnosExtra) { this.turnosExtra = turnosExtra; }

    public int getDañoVeneno() { return dañoVeneno; }
    public void setDañoVeneno(int dv) { this.dañoVeneno = dv; }

    public void gastarTurnoExtra() {
        if (this.turnosExtra > 0) {
            this.turnosExtra--;
        }
    }

    public int getDaño() {
        if (arma == null) return 1;
        return this.arma.getMagnitud();
    }

    public Mochila getMochila() { return mochila; }
}