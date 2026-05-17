package modelo;

public abstract class Persona { 
    /* Funciona como un contador global para asignar IDs. De momento le asignamos por orden el id a cada usuario,
       mas adelante v podemos hacer para que ellos creen el suyo propio */
    private static int contadorId = 1;

    // Atributos privados (Encapsulación)
    private int id;
    private String nombre;
    private String contraseña;

    // Constructor de nuestra clase "madre"
    public Persona(String nombre, String contraseña) {
        this.id = contadorId;
        contadorId++; // Incrementa para que el siguiente registrado tenga el siguiente ID
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