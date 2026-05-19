package modelo;

public class Usuario {

    private String nombre;
    private String contraseña;
    private boolean administrador;

    public Usuario(String nombre, String contraseña, boolean administrador) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.administrador = administrador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public boolean isAdministrador() {
        return administrador;
    }
}