package ControlGen;

import VistaGen.VistaManager;
import VistaGen.VistaLogin;
import ModeloGen.Usuario;
import ModeloGen.GestorUsuarios;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorManager implements ActionListener {
    private VistaManager vista;
    private Usuario usuario;
    private GestorUsuarios gestor;

    public ControladorManager(VistaManager vista, Usuario usuario, GestorUsuarios gestor) {
        this.vista = vista;
        this.usuario = usuario;
        this.gestor = gestor;
        this.vista.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Manager:ObjectBound":
                try {
                    Class<?> mainClase = Class.forName("Main");
                    mainClase.getMethod("iniciar", String.class).invoke(null, usuario.getNombre());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudo abrir ObjectBound:\n" + ex.getMessage());
                }
                break;
            case "Manager:Pasapalabra":
                JOptionPane.showMessageDialog(vista, "Pasapalabra: pendiente.");
                break;
            case "Manager:Estadisticas":
                JOptionPane.showMessageDialog(vista, "Estadisticas: pendiente.");
                break;
            case "Manager:Continuar":
                JOptionPane.showMessageDialog(vista, "Continuar partida: pendiente.");
                break;
            case "Manager:Admin":
                JOptionPane.showMessageDialog(vista, "Panel admin: pendiente.");
                break;
            case "Manager:CerrarSesion":
                vista.dispose();
                VistaLogin login = new VistaLogin();
                new ControladorLogin(login, gestor);
                break;
        }
    }
}