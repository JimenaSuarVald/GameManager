package Control;

import Vista.MenuPrincipal;
import Vista.SelecciónDeClase;
import Vista.VistaOpciones;
import Modelo.Usuario;
import Modelo.GestorArchivos;
import Modelo.GestorPartidas;
import Modelo.LogicaJuego;
import Modelo.ClaseJugador;
import Modelo.Item;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenu implements ActionListener {
    private MenuPrincipal menuPrincipal;
    private Usuario usuario;
    private VistaOpciones vistaOpciones;

    public ControladorMenu(MenuPrincipal menuPrincipal, Usuario usuario) {
        this.menuPrincipal = menuPrincipal;
        this.usuario = usuario;
        this.menuPrincipal.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Nueva Partida")) {
            String nombre = JOptionPane.showInputDialog(
                    menuPrincipal, "¿Cuál es tu nombre, héroe?",
                    "Nueva Partida", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null || nombre.trim().isEmpty()) return;

            GestorArchivos gestor = new GestorArchivos();
            ClaseJugador[] clases = gestor.cargarClases("Juego/Listas/Clases");
            SelecciónDeClase vistaClase = new SelecciónDeClase(clases);
            new ControladorSelecciónClase(vistaClase, menuPrincipal, usuario, nombre, clases);
            menuPrincipal.setVisible(false);

        } else if (comando.equals("Continuar Juego")) {
            GestorArchivos gestor = new GestorArchivos();
            Item[] items = gestor.cargarItems("Juego/Listas/Objetos");
            ClaseJugador[] clases = gestor.cargarClases("Juego/Listas/Clases");
            GestorPartidas gp = new GestorPartidas(items, clases);
            if (!gp.existePartida(usuario)) {
                JOptionPane.showMessageDialog(menuPrincipal, "No tienes ninguna partida guardada.");
                return;
            }
            LogicaJuego partida = gp.cargar(usuario);
            menuPrincipal.setVisible(false);
            new ControladorJuego(partida, menuPrincipal);

        } else if (comando.equals("Opciones")) {
            vistaOpciones = new VistaOpciones(menuPrincipal, usuario);
            vistaOpciones.setControlador(this);
            vistaOpciones.setVisible(true);

        } else if (comando.equals("Opciones:BorrarPartida")) {
            GestorArchivos gestor = new GestorArchivos();
            Item[] items = gestor.cargarItems("Juego/Listas/Objetos");
            ClaseJugador[] clases = gestor.cargarClases("Juego/Listas/Clases");
            GestorPartidas gp = new GestorPartidas(items, clases);
            if (gp.existePartida(usuario)) {
                gp.borrar(usuario);
                JOptionPane.showMessageDialog(vistaOpciones, "Partida borrada.");
            } else {
                JOptionPane.showMessageDialog(vistaOpciones, "No tienes ninguna partida guardada.");
            }

        } else if (comando.equals("Opciones:Cerrar")) {
            if (vistaOpciones != null) vistaOpciones.dispose();

        } else if (comando.equals("Salir")) {
            menuPrincipal.dispose();
        }
    }
}