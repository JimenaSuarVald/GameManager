package ModeloGen;

public class Persona {
    private String nombre, contraseña;
    private static int contadorId = 0;
    int id;

    public Persona(String Nombre, String Contraseña) {
        this.nombre = Nombre;
        this.contraseña = Contraseña;
        this.id = id;
        contadorId++;
    }


    public String getNombre() {return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }


    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getId() {
        return id;
    }
}
