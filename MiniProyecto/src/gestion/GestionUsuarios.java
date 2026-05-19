package gestion;

import java.util.ArrayList;

import modelo.Administrador;
import modelo.Jugador;
import modelo.Persona;

public class GestionUsuarios {

    private ArrayList<Persona> usuarios;

    public GestionUsuarios() {
        usuarios = new ArrayList<>();

        usuarios.add(new Administrador("admin", "admin123"));
        usuarios.add(new Jugador("jose", "1234"));
    }

    public boolean registrarJugador(String nombre, String contraseña) {

        if (existeUsuario(nombre)) {
            return false;
        }

        usuarios.add(new Jugador(nombre, contraseña));
        return true;
    }

    public Persona iniciarSesion(String nombre, String contraseña) {

        for (Persona persona : usuarios) {

            if (persona.getNombre().equals(nombre)
                    && persona.getContraseña().equals(contraseña)) {

                persona.incrementarLogin();
                return persona;
            }
        }

        return null;
    }

    public boolean existeUsuario(String nombre) {

        for (Persona persona : usuarios) {

            if (persona.getNombre().equals(nombre)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Persona> getUsuarios() {
        return usuarios;
    }
}