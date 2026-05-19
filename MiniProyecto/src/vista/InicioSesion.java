package vista;

import java.util.Scanner;

import gestion.GestionUsuarios;
import modelo.Persona;

public class InicioSesion {

    private Scanner teclado;
    private GestionUsuarios gestionUsuarios;

    public InicioSesion(GestionUsuarios gestionUsuarios) {
        this.gestionUsuarios = gestionUsuarios;
        this.teclado = new Scanner(System.in);
    }

    public Persona mostrarInicio() {

        int opcion;

        do {
            System.out.println("\n===== GAME MANAGER =====");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    return iniciarSesion();

                case 2:
                    registrarUsuario();
                    break;

                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;

                default:
                    System.out.println("Opción incorrecta.");
            }

        } while (opcion != 0);

        return null;
    }

    private Persona iniciarSesion() {

        System.out.println("\n===== INICIO DE SESIÓN =====");

        System.out.print("Usuario: ");
        String nombre = teclado.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = teclado.nextLine();

        Persona persona = gestionUsuarios.iniciarSesion(nombre, contraseña);

        if (persona != null) {
            System.out.println("Inicio de sesión correcto.");
            return persona;
        }

        System.out.println("Usuario o contraseña incorrectos.");
        return null;
    }

    private void registrarUsuario() {

        System.out.println("\n===== REGISTRO =====");

        System.out.print("Nuevo usuario: ");
        String nombre = teclado.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = teclado.nextLine();

        boolean registrado = gestionUsuarios.registrarJugador(nombre, contraseña);

        if (registrado) {
            System.out.println("Usuario registrado correctamente.");
        } else {
            System.out.println("Ese usuario ya existe.");
        }
    }
}