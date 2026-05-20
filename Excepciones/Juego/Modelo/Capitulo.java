package Modelo;

public class Capitulo {
    private String textoNarrativo;
    private String[] nombresEnemigos;
    private String[] nombresRecompensas;
    private String escenario;

    public Capitulo(String textoNarrativo, String[] nombresEnemigos,
                    String[] nombresRecompensas, String escenario) {
        this.textoNarrativo = textoNarrativo;
        this.nombresEnemigos = nombresEnemigos;
        this.nombresRecompensas = nombresRecompensas;
        this.escenario = escenario;
    }

    public String getTextoNarrativo() { return textoNarrativo; }
    public String[] getNombresEnemigos() { return nombresEnemigos; }
    public String[] getNombresRecompensas() { return nombresRecompensas; }
    public String getEscenario() { return escenario; }

    public boolean tieneCombate() {
        return nombresEnemigos != null && nombresEnemigos.length > 0;
    }

    public boolean tieneRecompensas() {
        return nombresRecompensas != null && nombresRecompensas.length > 0;
    }
}