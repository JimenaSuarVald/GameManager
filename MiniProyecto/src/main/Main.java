package main;

import gestion.GestionUsuarios;
import modelo.Persona;
import vista.InicioSesion;
import vista.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        GestionUsuarios gestionUsuarios = new GestionUsuarios();

        InicioSesion inicioSesion = new InicioSesion(gestionUsuarios);

        Persona persona = inicioSesion.mostrarInicio();

        if (persona != null) {

            MenuPrincipal menu = new MenuPrincipal(gestionUsuarios);
            menu.mostrarMenu(persona);

        } else {

            System.out.println("Aplicación finalizada.");
        }
    }
}