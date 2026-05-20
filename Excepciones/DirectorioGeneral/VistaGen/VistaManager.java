package VistaGen;

import Vista.PanelFondo;
import Modelo.RutaArchivos;
import ModeloGen.Persona;
import ModeloGen.Administrador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class VistaManager extends JFrame {
    private JButton botonPasapalabra;
    private JButton botonObjectBound;
    private JButton botonEstadisticas;
    private JButton botonAdmin;
    private JButton botonCerrarSesion;

    public VistaManager(Persona persona) {
        this.setTitle("Game Manager");
        this.setSize(700, 580);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        PanelFondo fondo = new PanelFondo(new BorderLayout(10, 10));
        fondo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Cabecera
        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setOpaque(false);

        JLabel titulo = new JLabel("GAME MANAGER");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        cabecera.add(titulo, BorderLayout.WEST);

        JPanel datosUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        datosUsuario.setOpaque(false);

        String etiqueta = "Conectado: " + persona.getNombre();
        JLabel info = new JLabel(etiqueta);
        info.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        info.setFont(new Font("SansSerif", Font.PLAIN, 13));
        datosUsuario.add(info);

        botonCerrarSesion = new JButton("Cerrar sesion");
        botonCerrarSesion.setActionCommand("Manager:CerrarSesion");
        datosUsuario.add(botonCerrarSesion);

        cabecera.add(datosUsuario, BorderLayout.EAST);
        fondo.add(cabecera, BorderLayout.NORTH);

        // Tarjetas de juegos
        JPanel panelJuegos = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));

        botonPasapalabra = new JButton("Jugar");
        botonPasapalabra.setActionCommand("Manager:Pasapalabra");
        panelJuegos.add(crearTarjeta("Pasapalabra", "pasapalabra", botonPasapalabra));

        botonObjectBound = new JButton("Jugar");
        botonObjectBound.setActionCommand("Manager:ObjectBound");
        panelJuegos.add(crearTarjeta("ObjectBound", "objectbound", botonObjectBound));

        fondo.add(panelJuegos, BorderLayout.CENTER);

        // Pie con estadísticas y admin
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        pie.setOpaque(false);

        botonEstadisticas = new JButton("Mis estadisticas");
        botonEstadisticas.setActionCommand("Manager:Estadisticas");
        pie.add(botonEstadisticas);

        if (persona instanceof Administrador) {
            botonAdmin = new JButton("Panel admin (Ranking)");
            botonAdmin.setActionCommand("Manager:Admin");
            pie.add(botonAdmin);
        }

        fondo.add(pie, BorderLayout.SOUTH);
        this.setContentPane(fondo);
        this.setVisible(true);
    }

    private JPanel crearTarjeta(String nombre, String archivoImagen, JButton botonJugar) {
        JPanel tarjeta = new JPanel(new BorderLayout(5, 8));
        tarjeta.setPreferredSize(new Dimension(220, 280));

        JLabel titulo = new JLabel(nombre, JLabel.CENTER);
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        tarjeta.add(titulo, BorderLayout.NORTH);

        JLabel imagen = new JLabel("", JLabel.CENTER);
        String ruta = RutaArchivos.resolver("DirectorioGeneral/RecursosGen/Juegos/" + archivoImagen + ".png");
        File f = new File(ruta);
        if (f.exists()) {
            Image img = new ImageIcon(ruta).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            imagen.setIcon(new ImageIcon(img));
        } else {
            imagen.setText("(Sin imagen)");
            imagen.setForeground(Color.LIGHT_GRAY);
            imagen.setPreferredSize(new Dimension(160, 160));
        }
        tarjeta.add(imagen, BorderLayout.CENTER);
        tarjeta.add(botonJugar, BorderLayout.SOUTH);
        return tarjeta;
    }

    public void setControlador(ActionListener c) {
        botonPasapalabra.addActionListener(c);
        botonObjectBound.addActionListener(c);
        botonEstadisticas.addActionListener(c);
        if (botonAdmin != null) botonAdmin.addActionListener(c);
        botonCerrarSesion.addActionListener(c);
    }
}