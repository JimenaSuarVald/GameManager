package ModeloGen;

public class JugadorActivo {
    private static Persona actual;
    private static GestionUsuarios gestion;

    public static void set(Persona p, GestionUsuarios g) {
        actual = p;
        gestion = g;
    }

    public static Persona get() { return actual; }

    public static void registrarPartida(int puntos, boolean victoria) {
        if (actual instanceof Jugador) {
            ((Jugador) actual).registrarPartida(puntos, victoria);
            if (gestion != null) gestion.guardarUsuarios();
        }
    }
}