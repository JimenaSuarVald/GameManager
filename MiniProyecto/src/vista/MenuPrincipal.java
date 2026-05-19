package vista;

import java.util.Scanner;

import modelo.Administrador;
import modelo.Persona;

public class MenuPrincipal {

    private Scanner teclado;

    public MenuPrincipal() {

        teclado = new Scanner(System.in);
    }

    public void mostrarMenu(Persona persona) {

        int opcion;

        do {

            System.out.println("\n===== GAME MANAGER =====");

            System.out.println("Usuario: " + persona.getNombre());

            System.out.println("1. Jugar");
            System.out.println("2. Estadísticas");

            if (persona instanceof Administrador) {

                System.out.println("3. Panel administrador");
            }

            System.out.println("0. Salir");

            opcion = teclado.nextInt();

        } while (opcion != 0);
    }
}