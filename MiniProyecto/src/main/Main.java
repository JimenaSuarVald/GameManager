package main;

import modelo.Persona;
import vista.InicioSesion;
import vista.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        InicioSesion inicioSesion = new InicioSesion();

        Persona usuario = inicioSesion.iniciarSesion();

        if (usuario != null) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.mostrarMenu(usuario);
        } else {
            System.out.println("No se puede acceder al menú principal.");
        }
    }
}