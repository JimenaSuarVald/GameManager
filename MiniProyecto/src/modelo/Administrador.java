package modelo;

public class Administrador extends Persona {

    public Administrador(String nombre, String contraseña) {

        super(nombre, contraseña);
    }

    public void verUsuarios() {

        System.out.println("Mostrando usuarios...");
    }
}