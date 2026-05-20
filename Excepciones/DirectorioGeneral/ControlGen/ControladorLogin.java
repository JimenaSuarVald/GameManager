package ControlGen;

import VistaGen.VistaLogin;
import VistaGen.VistaManager;
import ModeloGen.Persona;
import ModeloGen.GestionUsuarios;
import ModeloGen.JugadorActivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin implements ActionListener {
    private VistaLogin vista;
    private GestionUsuarios gestion;

    public ControladorLogin(VistaLogin vista, GestionUsuarios gestion) {
        this.vista = vista;
        this.gestion = gestion;
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
            Persona p = gestion.iniciarSesion(nombre, contraseña);
            if (p == null) {
                vista.mostrarError("Usuario o contrasena incorrectos.");
            } else {
                JugadorActivo.set(p, gestion);
                vista.dispose();
                VistaManager menu = new VistaManager(p);
                new ControladorManager(menu, p, gestion);
            }
        }
    }
}