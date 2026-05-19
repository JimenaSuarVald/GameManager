package vista;

import java.util.Scanner;

import gestion.GestionUsuarios;
import modelo.Persona;

public class InicioSesion {

    private Scanner teclado;
    private GestionUsuarios gestion;

    public InicioSesion() {

        teclado = new Scanner(System.in);
        gestion = new GestionUsuarios();
    }

    public Persona iniciarSesion() {

        System.out.println("===== INICIO DE SESIÓN =====");

        System.out.print("Usuario: ");
        String nombre = teclado.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = teclado.nextLine();

        Persona persona = gestion.iniciarSesion(nombre, contraseña);

        if (persona != null) {

            System.out.println("Inicio correcto");

            return persona;
        }

        System.out.println("Datos incorrectos");

        return null;
    }
}