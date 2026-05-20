package ModeloGen;

public class GestorEstadisticas {

    public String estadisticasJugador(Jugador jugador) {
        return "===== TUS ESTADISTICAS =====\n"
                + "Jugador: " + jugador.getNombre() + "\n"
                + "Puntuacion total: " + jugador.getPuntuacionTotal() + "\n"
                + "Record: " + jugador.getRecord() + "\n"
                + "Partidas jugadas: " + jugador.getPartidasJugadas() + "\n"
                + "Victorias: " + jugador.getVictorias() + "\n"
                + "Numero de logins: " + jugador.getNumeroLogins();
    }

    public String ranking(Persona[] usuarios) {
        String r = "===== RANKING DE JUGADORES =====\n";
        boolean hay = false;
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] instanceof Jugador) {
                Jugador j = (Jugador) usuarios[i];
                r += j.getNombre()
                        + "Puntos: " + j.getPuntuacionTotal()
                        + "Record: " + j.getRecord()
                        + "Partidas: " + j.getPartidasJugadas()
                        + "Victorias: " + j.getVictorias()
                        + "\n";
                hay = true;
            }
        }
        if (!hay) r += "(No hay jugadores registrados todavia)\n";
        return r;
    }
}