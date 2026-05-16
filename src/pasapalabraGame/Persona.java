package pasapalabraGame;

public class Persona {
    private String Nombre, Contraseña;
    private int Id = 00000;

    public Persona(String Nombre, String Contraseña) {
        this.Nombre = Nombre;
        this.Contraseña = Contraseña;
        this.Id = Id;
        Id++;
    }

	public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
}
