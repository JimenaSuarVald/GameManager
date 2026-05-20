package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPausa extends JFrame {

    private JPanel panelPrincipal, panel;
    private JButton Continuar, Opciones, Mochila, Guardar, VolverMenu;

    public MenuPausa() {
        panel = new JPanel();
        panelPrincipal = new JPanel();

        Continuar = new JButton("Continuar");
        Continuar.setActionCommand("Pausa:Continuar");

        Mochila = new JButton("Mochila");
        Mochila.setActionCommand("Pausa:Mochila");

        Opciones = new JButton("Opciones");
        Opciones.setActionCommand("Pausa:Opciones");

        Guardar = new JButton("Guardar");
        Guardar.setActionCommand("Pausa:Guardar");

        VolverMenu = new JButton("Volver al menú");
        VolverMenu.setActionCommand("Pausa:Menu");

        this.setTitle("ObjectBound - Menú de Pausa");
        this.setSize(300, 350);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.add(Continuar);
        panel.add(Mochila);
        panel.add(Opciones);
        panel.add(Guardar);
        panel.add(VolverMenu);

        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.add(panel);

        this.add(panelPrincipal);
        this.setVisible(false);
    }

    public void setControlador(ActionListener c) {
        Continuar.addActionListener(c);
        Mochila.addActionListener(c);
        Opciones.addActionListener(c);
        Guardar.addActionListener(c);
        VolverMenu.addActionListener(c);
    }
}