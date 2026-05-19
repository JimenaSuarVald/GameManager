package modelo;

public class Persona {

    protected String nombre;
    protected String contraseña;
    protected int numeroLogins;

    public Persona(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.numeroLogins = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public int getNumeroLogins() {
        return numeroLogins;
    }

    public void incrementarLogin() {
        numeroLogins++;
    }
}