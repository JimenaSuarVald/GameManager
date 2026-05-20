package Vista;

import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaOpciones extends JDialog {
    private JButton botonBorrarPartida;
    private JButton botonCerrar;

    public VistaOpciones(JFrame padre, Usuario usuario) {
        super(padre, "Opciones", true);
        this.setSize(400, 220);
        this.setLocationRelativeTo(padre);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Opciones", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));

        JLabel info = new JLabel(
                "<html><center>Usuario: " + usuario.getNombre() +
                        "<br><br>Puedes borrar tu partida guardada para empezar de cero.</center></html>",
                JLabel.CENTER);

        botonBorrarPartida = new JButton("Borrar partida guardada");
        botonBorrarPartida.setActionCommand("Opciones:BorrarPartida");
        botonCerrar = new JButton("Cerrar");
        botonCerrar.setActionCommand("Opciones:Cerrar");

        JPanel panelTexto = new JPanel(new GridLayout(2, 1, 5, 10));
        panelTexto.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelTexto.add(titulo);
        panelTexto.add(info);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        panelBotones.add(botonBorrarPartida);
        panelBotones.add(botonCerrar);

        this.setLayout(new BorderLayout(5, 5));
        this.add(panelTexto, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);
    }

    public void setControlador(ActionListener c) {
        botonBorrarPartida.addActionListener(c);
        botonCerrar.addActionListener(c);
    }
}