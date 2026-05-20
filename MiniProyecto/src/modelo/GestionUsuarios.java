package modelo;

import java.util.ArrayList;

public class GestionUsuarios {

    // Lista unica donde se guarda todo mientras el programa esta abierto
    private ArrayList<Persona> listaPersonas;

    public GestionUsuarios() {
        // Intentamos cargar los usuarios guardados del txt
        this.listaPersonas = GestorArchivos.cargarUsuarios();
        if (this.listaPersonas == null) {
            this.listaPersonas = new ArrayList<>();
        }

    }

    // Registro del jugador (dar de alta)
    public boolean registrarJugador(String nombre, String contraseña) {
        // Comprobamos que el nombre no este ya pillado
        if (buscarUsuarioPorNombre(nombre) != null) {
            return false;
        }

        Jugador nuevo = new Jugador(nombre, contraseña);
        listaPersonas.add(nuevo);

        // Guardamos en el txt para que no se pierda
        GestorArchivos.guardarUsuarios(listaPersonas);
        return true;
    }

    // Comprobacion del login
    public Persona comprobarAutenticacion(String nombre, String contraseña) {
        Persona usuario = buscarUsuarioPorNombre(nombre);

        if (usuario != null && usuario.getContraseña().equals(contraseña)) {

            // Si es jugador le sumamos un acceso
            if (usuario instanceof Jugador) {
                ((Jugador) usuario).incrementarAccesos();
                GestorArchivos.guardarUsuarios(listaPersonas);
            }
            return usuario;
        }
        return null;
    }

    // Buscador interno por nombre (sin distinguir mayusculas)
    private Persona buscarUsuarioPorNombre(String nombre) {
        for (Persona p : listaPersonas) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    /* Metodo para guardar desde fuera cuando se actualiza algo
       por ejemplo al terminar una partida y actualizar puntuacion */
    public void guardar() {
        GestorArchivos.guardarUsuarios(listaPersonas);
    }

    // Getter para que el admin pueda ver la lista completa
    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }
}