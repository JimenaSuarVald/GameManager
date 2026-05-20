package ModeloGen;

public class GestorUsuarios {
    private Usuario[] usuarios;

    public GestorUsuarios() {
        usuarios = new Usuario[2];
        usuarios[0] = new Usuario("admin", "admin123", true);
        usuarios[1] = new Usuario("jose", "1234", false);
    }

    public Usuario autenticar(String nombre, String contraseña) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null
                    && usuarios[i].getNombre().equals(nombre)
                    && usuarios[i].getContraseña().equals(contraseña)) {
                return usuarios[i];
            }
        }
        return null;
    }
}