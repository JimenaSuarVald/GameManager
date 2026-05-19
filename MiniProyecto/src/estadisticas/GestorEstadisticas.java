package estadisticas;

import java.util.ArrayList;

import modelo.Jugador;
import modelo.Persona;

public class GestorEstadisticas {

    public void mostrarEstadisticasJugador(Jugador jugador) {

        System.out.println("\n===== ESTADÍSTICAS DEL JUGADOR =====");
        System.out.println("Jugador: " + jugador.getNombre());
        System.out.println("Puntuación total: " + jugador.getPuntuacionTotal());
        System.out.println("Récord: " + jugador.getRecord());
        System.out.println("Partidas jugadas: " + jugador.getPartidasJugadas());
        System.out.println("Victorias: " + jugador.getVictorias());
        System.out.println("Número de logins: " + jugador.getNumeroLogins());
    }

    public void mostrarRanking(ArrayList<Persona> usuarios) {

        System.out.println("\n===== RANKING DE JUGADORES =====");

        for (Persona persona : usuarios) {

            if (persona instanceof Jugador) {

                Jugador jugador = (Jugador) persona;

                System.out.println(
                        jugador.getNombre()
                        + " | Puntos: " + jugador.getPuntuacionTotal()
                        + " | Récord: " + jugador.getRecord()
                        + " | Partidas: " + jugador.getPartidasJugadas()
                );
            }
        }
    }
}