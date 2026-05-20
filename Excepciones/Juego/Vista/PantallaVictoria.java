package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PantallaVictoria extends JFrame {
    private JButton botonMenu;
    private JButton botonSalir;
    private PanelFondo panelFondo;

    public PantallaVictoria() {
        this.setTitle("ObjectBound - Victoria");
        this.setSize(500, 350);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panelFondo = new PanelFondo(new BorderLayout(10, 10));
        panelFondo.cargarFondo("Juego/Recursos/UI/victoria.png");

        JLabel titulo = new JLabel("¡VICTORIA!", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setBackground(new Color(0, 0, 0, 180));
        titulo.setOpaque(true);

        JLabel mensaje = new JLabel("Has completado la historia.", JLabel.CENTER);
        mensaje.setForeground(Color.WHITE);
        mensaje.setBackground(new Color(0, 0, 0, 150));
        mensaje.setOpaque(true);
        mensaje.setFont(new Font("SansSerif", Font.BOLD, 16));

        botonMenu = new JButton("Volver al menu");
        botonMenu.setActionCommand("FinJuego:Menu");
        botonSalir = new JButton("Salir");
        botonSalir.setActionCommand("FinJuego:Salir");

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.setOpaque(false);
        panelBotones.add(botonMenu);
        panelBotones.add(botonSalir);

        JPanel panelMensaje = new JPanel(new GridLayout(2, 1, 5, 5));
        panelMensaje.setOpaque(false);
        panelMensaje.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelMensaje.add(titulo);
        panelMensaje.add(mensaje);

        panelFondo.add(panelMensaje, BorderLayout.CENTER);
        panelFondo.add(panelBotones, BorderLayout.SOUTH);

        this.setContentPane(panelFondo);
        this.setVisible(true);
    }

    public void setControlador(ActionListener c) {
        botonMenu.addActionListener(c);
        botonSalir.addActionListener(c);
    }
}