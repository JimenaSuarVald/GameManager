package ControlGen;

import VistaGen.VistaLogin;
import VistaGen.VistaManager;
import ModeloGen.Usuario;
import ModeloGen.GestorUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin implements ActionListener {
    private VistaLogin vista;
    private GestorUsuarios gestor;

    public ControladorLogin(VistaLogin vista, GestorUsuarios gestor) {
        this.vista = vista;
        this.gestor = gestor;
        this.vista.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login:Entrar")) {
            String nombre = vista.getNombreUsuario();
            String contraseña = vista.getContraseña();
            if (nombre.isEmpty() || contraseña.isEmpty()) {
                vista.mostrarError("Introduce usuario y contrasena.");
                return;
            }
            Usuario u = gestor.autenticar(nombre, contraseña);
            if (u == null) {
                vista.mostrarError("Usuario o contrasena incorrectos.");
            } else {
                vista.dispose();
                VistaManager menu = new VistaManager(u);
                new ControladorManager(menu, u, gestor);
            }
        }
    }
}