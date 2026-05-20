import VistaGen.VistaLogin;
import ModeloGen.GestionUsuarios;
import ControlGen.ControladorLogin;

public class MainManager {
    public static void main(String[] args) {
        GestionUsuarios gestion = new GestionUsuarios();
        VistaLogin login = new VistaLogin();
        new ControladorLogin(login, gestion);
    }
}