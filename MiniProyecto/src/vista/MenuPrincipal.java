package vista;

import java.util.Scanner;

import estadisticas.GestorEstadisticas;
import gestion.GestionUsuarios;
import modelo.Administrador;
import modelo.Jugador;
import modelo.Persona;

public class MenuPrincipal {

    private Scanner teclado;
    private GestionUsuarios gestionUsuarios;
    private GestorEstadisticas gestorEstadisticas;

    public MenuPrincipal(GestionUsuarios gestionUsuarios) {
        this.gestionUsuarios = gestionUsuarios;
        this.gestorEstadisticas = new GestorEstadisticas();
        this.teclado = new Scanner(System.in);
    }

    public void mostrarMenu(Persona persona) {

        int opcion;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("Usuario conectado: " + persona.getNombre());
            System.out.println("Logins realizados: " + persona.getNumeroLogins());

            System.out.println("1. Jugar Pasapalabra");
            System.out.println("2. Jugar Ahorcado / Tres en raya");
            System.out.println("3. Ver mis estadísticas");
            System.out.println("4. Continuar partida");

            if (persona instanceof Administrador) {
                System.out.println("5. Panel administrador");
            }

            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    simularPartida(persona, "Pasapalabra");
                    break;

                case 2:
                    simularPartida(persona, "Ahorcado");
                    break;

                case 3:
                    verMisEstadisticas(persona);
                    break;

                case 4:
                    System.out.println("Funcionalidad de continuar partida pendiente de integrar.");
                    break;

                case 5:
                    if (persona instanceof Administrador) {
                        gestorEstadisticas.mostrarRanking(gestionUsuarios.getUsuarios());
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 0:
                    System.out.println("Cerrando sesión...");
                    break;

                default:
                    System.out.println("Opción incorrecta.");
            }

        } while (opcion != 0);
    }

    private void simularPartida(Persona persona, String juego) {

        if (persona instanceof Jugador) {

            System.out.print("Introduce puntuación obtenida en " + juego + ": ");
            int puntos = teclado.nextInt();

            System.out.print("¿Ha ganado la partida? 1 = Sí, 0 = No: ");
            int resultado = teclado.nextInt();

            boolean victoria = resultado == 1;

            Jugador jugador = (Jugador) persona;
            jugador.registrarPartida(puntos, victoria);

            System.out.println("Partida registrada correctamente.");

        } else {
            System.out.println("El administrador no registra partidas como jugador.");
        }
    }

    private void verMisEstadisticas(Persona persona) {

        if (persona instanceof Jugador) {

            Jugador jugador = (Jugador) persona;
            gestorEstadisticas.mostrarEstadisticasJugador(jugador);

        } else {
            System.out.println("El administrador puede consultar el ranking desde el panel administrador.");
        }
    }
}