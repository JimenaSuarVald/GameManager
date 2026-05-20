package ControlGen;

import VistaGen.VistaManager;
import VistaGen.VistaLogin;
import ModeloGen.Persona;
import ModeloGen.Jugador;
import ModeloGen.Administrador;
import ModeloGen.GestionUsuarios;
import ModeloGen.GestorEstadisticas;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorManager implements ActionListener {
    private VistaManager vista;
    private Persona persona;
    private GestionUsuarios gestion;
    private GestorEstadisticas estadisticas;

    public ControladorManager(VistaManager vista, Persona persona, GestionUsuarios gestion) {
        this.vista = vista;
        this.persona = persona;
        this.gestion = gestion;
        this.estadisticas = new GestorEstadisticas();
        this.vista.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Manager:ObjectBound":
                try {
                    Class<?> mainClase = Class.forName("Main");
                    mainClase.getMethod("iniciar", String.class).invoke(null, persona.getNombre());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudo abrir ObjectBound:\n" + ex.getMessage());
                }
                break;
            case "Manager:Pasapalabra":
                try {
                    Class<?> clase = Class.forName("ModeloPP.pasapalabramain");
                    clase.getConstructor(String.class).newInstance(persona.getNombre());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudo abrir Pasapalabra:\n" + ex.getMessage());
                }
                break;
            case "Manager:Estadisticas":
                if (persona instanceof Jugador) {
                    String texto = estadisticas.estadisticasJugador((Jugador) persona);
                    JOptionPane.showMessageDialog(vista, texto, "Mis estadisticas",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(vista, "El administrador no juega partidas.");
                }
                break;
            case "Manager:Continuar":
                JOptionPane.showMessageDialog(vista, "Continuar partida: pendiente.");
                break;
            case "Manager:Admin":
                if (persona instanceof Administrador) {
                    String texto = estadisticas.ranking(gestion.getUsuarios());
                    JOptionPane.showMessageDialog(vista, texto, "Ranking",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(vista, "No tienes permisos de administrador.");
                }
                break;
            case "Manager:CerrarSesion":
                gestion.guardarUsuarios();
                vista.dispose();
                VistaLogin login = new VistaLogin();
                new ControladorLogin(login, gestion);
                break;
        }
    }
}