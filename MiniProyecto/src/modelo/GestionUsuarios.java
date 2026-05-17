package modelo;

import java.util.ArrayList;

public class GestionUsuarios {

    // La lista única donde se guardará todo mientras el programa esté encendido
    private ArrayList<Persona> listaPersonas;

    public GestionUsuarios() {
        this.listaPersonas = new ArrayList<>();
        
        // Esto son users de prueba pra testear q funcione sin hacer login
        this.listaPersonas.add(new Administrador("admin", "admin123"));
        this.listaPersonas.add(new Jugador("diego", "paso1"));
        this.listaPersonas.add(new Jugador("jose", "1234"));
    }

    // registro del jugador ( dar de alta)
    public boolean registrarJugador(String nombre, String contraseña) {
        // Comprobamos si el nombre ya existe para que no haya duplicados
        if (buscarUsuarioPorNombre(nombre) != null) {
            return false; // si está pillado el nombre te da false
        }
        
        // Si está libre, creamos el jugador y lo añadimos
        Jugador nuevo = new Jugador(nombre, contraseña);
        listaPersonas.add(nuevo);
        return true; // Registro completado
    }

    // metodo de comprobacion del login para ver si es correcto
    public Persona comprobarAutenticacion(String nombre, String contraseña) {
        Persona usuario = buscarUsuarioPorNombre(nombre);
        
        // Si existe y la contraseña coincide, lo devolvemos
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            // Si además es un jugador, aprovechamos para sumarle el acceso (lo que pedía Crisenti que está en la calse perosna)
            if (usuario instanceof Jugador) {
                ((Jugador) usuario).incrementarAccesos();
            }
            return usuario;
        }
        return null; // Si falla devuelve null
    }

    // metodo buscador de usuarios ( aqui tengo algunas dudas y a partir de aqui he tirado de gemini, queda darle una vuelta para entenderlo
      private Persona buscarUsuarioPorNombre(String nombre) {
        for (Persona p : listaPersonas) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p; // Encontrado
            }
        }
        return null; // No existe
    }

    // GETTER PARA EL ADMIN 
    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }
}
