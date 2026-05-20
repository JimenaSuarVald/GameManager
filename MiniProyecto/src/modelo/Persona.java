package modelo;

public abstract class Persona { 
//Contador de IDS
    private static int contadorId = 1;

    // Atributos privados (Encapsulación)
    private int id;
    private String nombre;
    private String contraseña;

    // Constructor de nuestra clase "madre"
    public Persona(String nombre, String contraseña) {
        this.id = contadorId;
        contadorId++; 
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    // getters y setters para el login
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}