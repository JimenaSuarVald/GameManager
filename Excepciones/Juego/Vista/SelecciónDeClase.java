package Vista;

import Modelo.ClaseJugador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelecciónDeClase extends JFrame {

    private JButton[] botonesClase;

    public SelecciónDeClase(ClaseJugador[] clases) {
        this.setTitle("ObjectBound - Selección de Clase");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(1, clases.length, 10, 10));

        botonesClase = new JButton[clases.length];

        for (int i = 0; i < clases.length; i++) {
            if (clases[i] == null) continue;

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createTitledBorder(clases[i].getNombre()));

            JTextArea info = new JTextArea();
            info.setEditable(false);
            info.setText(
                    "Tipo: " + clases[i].getTipoDaño() + "\n\n" +
                            "Habilidades:\n" +
                            "- " + clases[i].getHabilidades()[0] + "\n" +
                            "- " + clases[i].getHabilidades()[1] + "\n" +
                            "- " + clases[i].getHabilidades()[2]
            );
            panel.add(info, BorderLayout.CENTER);

            JButton boton = new JButton("Elegir " + clases[i].getNombre());
            boton.setActionCommand("CLASE:" + i);
            botonesClase[i] = boton;
            panel.add(boton, BorderLayout.SOUTH);

            this.add(panel);
        }

        this.setVisible(true);
    }

    public void setControlador(ActionListener c) {
        for (JButton boton : botonesClase) {
            if (boton != null) {
                boton.addActionListener(c);
            }
        }
    }
}