package VistaGen;

import Vista.PanelFondo;
import Modelo.RutaArchivos;
import ModeloGen.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class VistaManager extends JFrame {
    private JButton botonPasapalabra;
    private JButton botonObjectBound;
    private JButton botonEstadisticas;
    private JButton botonContinuar;
    private JButton botonAdmin;
    private JButton botonCerrarSesion;

    public VistaManager(Usuario usuario) {
        this.setTitle("Game Manager");
        this.setSize(700, 580);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        PanelFondo fondo = new PanelFondo(new BorderLayout(10, 10));
        fondo.cargarFondo("DirectorioGeneral/Recursos/UI/fondo_menu.png");
        fondo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setOpaque(false);

        JLabel titulo = new JLabel("GAME MANAGER");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(20, 20, 30));
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        cabecera.add(titulo, BorderLayout.WEST);

        JPanel datosUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        datosUsuario.setOpaque(false);

        String etiqueta = "Conectado: " + usuario.getNombre();
        if (usuario.isAdministrador()) etiqueta += " (admin)";
        JLabel info = new JLabel(etiqueta);
        info.setForeground(Color.WHITE);
        info.setOpaque(true);
        info.setBackground(new Color(20, 20, 30));
        info.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        info.setFont(new Font("SansSerif", Font.PLAIN, 14));
        datosUsuario.add(info);

        botonCerrarSesion = new JButton("Cerrar sesion");
        botonCerrarSesion.setActionCommand("Manager:CerrarSesion");
        datosUsuario.add(botonCerrarSesion);

        cabecera.add(datosUsuario, BorderLayout.EAST);
        fondo.add(cabecera, BorderLayout.NORTH);

        JPanel panelJuegos = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        panelJuegos.setOpaque(false);

        botonPasapalabra = new JButton("Jugar");
        botonPasapalabra.setActionCommand("Manager:Pasapalabra");
        panelJuegos.add(crearTarjeta("Pasapalabra", "pasapalabra", botonPasapalabra));

        botonObjectBound = new JButton("Jugar");
        botonObjectBound.setActionCommand("Manager:ObjectBound");
        panelJuegos.add(crearTarjeta("ObjectBound", "objectbound", botonObjectBound));

        fondo.add(panelJuegos, BorderLayout.CENTER);

        JPanel pie = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        pie.setOpaque(false);

        botonEstadisticas = new JButton("Estadisticas");
        botonEstadisticas.setActionCommand("Manager:Estadisticas");
        pie.add(botonEstadisticas);

        botonContinuar = new JButton("Continuar partida");
        botonContinuar.setActionCommand("Manager:Continuar");
        pie.add(botonContinuar);

        if (usuario.isAdministrador()) {
            botonAdmin = new JButton("Panel admin");
            botonAdmin.setActionCommand("Manager:Admin");
            pie.add(botonAdmin);
        }

        fondo.add(pie, BorderLayout.SOUTH);
        this.setContentPane(fondo);
        this.setVisible(true);
    }

    private JPanel crearTarjeta(String nombre, String archivoImagen, JButton botonJugar) {
        JPanel tarjeta = new JPanel(new BorderLayout(5, 8));
        tarjeta.setOpaque(true);
        tarjeta.setBackground(new Color(35, 35, 45));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        tarjeta.setPreferredSize(new Dimension(220, 280));

        JLabel titulo = new JLabel(nombre, JLabel.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        tarjeta.add(titulo, BorderLayout.NORTH);

        JLabel imagen = new JLabel("", JLabel.CENTER);
        String ruta = RutaArchivos.resolver("DirectorioGeneral/Recursos/Juegos/" + archivoImagen + ".png");
        File f = new File(ruta);
        if (f.exists()) {
            Image img = new ImageIcon(ruta).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            imagen.setIcon(new ImageIcon(img));
        } else {
            imagen.setText("(Sin imagen)");
            imagen.setForeground(Color.LIGHT_GRAY);
            imagen.setOpaque(true);
            imagen.setBackground(new Color(60, 60, 70));
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
        botonContinuar.addActionListener(c);
        if (botonAdmin != null) botonAdmin.addActionListener(c);
        botonCerrarSesion.addActionListener(c);
    }
}