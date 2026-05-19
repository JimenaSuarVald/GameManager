package vista;

import java.util.Scanner;

import modelo.Usuario;

public class MenuPrincipal {

    private Scanner teclado;

    public MenuPrincipal() {

        teclado = new Scanner(System.in);
    }

    public void mostrarMenu(Usuario usuario) {

        int opcion;

        do {

            System.out.println("\n===== GAME MANAGER =====");
            System.out.println("Usuario conectado: " + usuario.getNombre());

            System.out.println("1. Jugar Pasapalabra");
            System.out.println("2. Jugar Ahorcado");
            System.out.println("3. Ver estadísticas");
            System.out.println("4. Continuar partida");

            if (usuario.isAdministrador()) {

                System.out.println("5. Panel administrador");
            }

            System.out.println("0. Cerrar sesión");

            System.out.print("Seleccione una opción: ");

            opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Abriendo Pasapalabra...");
                    break;

                case 2:
                    System.out.println("Abriendo Ahorcado...");
                    break;

                case 3:
                    System.out.println("Abriendo estadísticas...");
                    break;

                case 4:
                    System.out.println("Continuando partida...");
                    break;

                case 5:

                    if (usuario.isAdministrador()) {

                        System.out.println("Abriendo panel administrador...");
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
}