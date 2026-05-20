package ModeloGen;

public class Persona {
    protected String nombre;
    protected String contraseña;
    protected int numeroLogins;

    public Persona(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.numeroLogins = 0;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public int getNumeroLogins() { return numeroLogins; }
    public void setNumeroLogins(int n) { this.numeroLogins = n; }
    public void incrementarLogin() { numeroLogins++; }
}