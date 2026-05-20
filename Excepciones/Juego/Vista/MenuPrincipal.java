package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    private JPanel panel;
    private PanelFondo panelFondo;
    private JButton NuevaPartida, ContinuarJuego, Opciones, Salir;

    public MenuPrincipal() {
        panel = new JPanel();
        panelFondo = new PanelFondo(new GridBagLayout());
        panelFondo.cargarFondo("Juego/Recursos/UI/menu.png");

        NuevaPartida = new JButton("Nueva Partida");
        ContinuarJuego = new JButton("Continuar Juego");
        Opciones = new JButton("Opciones");
        Salir = new JButton("Salir");

        this.setTitle("ObjectBound - Menu Principal");
        this.setSize(400, 400);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setOpaque(false);
        panel.add(NuevaPartida);
        panel.add(ContinuarJuego);
        panel.add(Opciones);
        panel.add(Salir);

        panelFondo.add(panel);
        this.setContentPane(panelFondo);

        this.setVisible(true);
    }

    public void setControlador(ActionListener c) {
        NuevaPartida.addActionListener(c);
        ContinuarJuego.addActionListener(c);
        Opciones.addActionListener(c);
        Salir.addActionListener(c);
    }
}