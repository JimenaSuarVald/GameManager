import VistaGen.VistaLogin;
import ModeloGen.GestorUsuarios;
import ControlGen.ControladorLogin;

public class MainManager {
    public static void main(String[] args) {
        GestorUsuarios gestor = new GestorUsuarios();
        VistaLogin login = new VistaLogin();
        new ControladorLogin(login, gestor);
    }
}