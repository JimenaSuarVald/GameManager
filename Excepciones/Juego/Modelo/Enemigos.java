package Modelo;

public class Enemigos {
    private String nombre, tipoDaño, rutaImagen;
    private int vida, daño, turnosCegado, dañoVeneno, turnosAturdido, turnosHipnotizado;

    public Enemigos(String nombre, int daño, int vida, String tipoDaño) {
        this.nombre = nombre;
        this.daño = daño;
        this.vida = vida;
        this.tipoDaño = tipoDaño;
        this.turnosCegado = 0;
        this.dañoVeneno = 0;
        this.turnosAturdido = 0;
        this.turnosHipnotizado = 0;
    }

    public String getNombre() { return nombre; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }

    public int getDaño() { return daño; }
    public String getTipoDaño() { return tipoDaño; }

    public int getTurnosCegado() { return turnosCegado; }
    public void setTurnosCegado(int turnosCegado) { this.turnosCegado = turnosCegado; }
    public void reducirCeguera() {
        if (this.turnosCegado > 0) this.turnosCegado--;
    }

    public int getDañoVeneno() { return dañoVeneno; }
    public void setDañoVeneno(int dañoVeneno) { this.dañoVeneno = dañoVeneno; }

    public int getTurnosAturdido() { return turnosAturdido; }
    public void setTurnosAturdido(int turnosAturdido) { this.turnosAturdido = turnosAturdido; }
    public void reducirAturdimiento() {
        if (this.turnosAturdido > 0) this.turnosAturdido--;
    }

    public int getTurnosHipnotizado() { return turnosHipnotizado; }
    public void setTurnosHipnotizado(int turnosHipnotizado) { this.turnosHipnotizado = turnosHipnotizado; }
    public void reducirHipnosis() {
        if (this.turnosHipnotizado > 0) this.turnosHipnotizado--;
    }
}