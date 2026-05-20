import Vista.MenuPrincipal;
import Control.ControladorMenu;
import Modelo.Usuario;
import ModeloGen.Persona;

public class Main {
    public static void iniciar(String nombreUsuario) {
        Persona personaPuente = new Persona(nombreUsuario, "");
        Usuario usuario = new Usuario(personaPuente, nombreUsuario);
        MenuPrincipal menu = new MenuPrincipal();
        new ControladorMenu(menu, usuario);
    }

    public static void main(String[] args) {
        iniciar("test");
    }
}