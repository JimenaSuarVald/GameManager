package VistaGen;

import Vista.PanelFondo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private JButton botonEntrar;
    private JLabel mensajeError;

    public VistaLogin() {
        this.setTitle("Game Manager - Inicio de Sesion");
        this.setSize(450, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        PanelFondo fondo = new PanelFondo(new GridBagLayout());
        fondo.cargarFondo("DirectorioGeneral/Recursos/UI/fondo_login.png");

        JPanel card = new JPanel(new GridBagLayout());
        card.setOpaque(true);
        card.setBackground(new Color(35, 35, 45));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(25, 35, 25, 35)
        ));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 5, 8, 5);

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        JLabel titulo = new JLabel("INICIO DE SESION");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        card.add(titulo, c);

        c.gridy = 1; c.gridwidth = 1;
        c.gridx = 0; c.anchor = GridBagConstraints.WEST;
        JLabel lblU = new JLabel("Usuario:");
        lblU.setForeground(Color.WHITE);
        lblU.setFont(new Font("SansSerif", Font.PLAIN, 14));
        card.add(lblU, c);

        c.gridx = 1;
        campoUsuario = new JTextField(15);
        card.add(campoUsuario, c);

        c.gridx = 0; c.gridy = 2;
        JLabel lblP = new JLabel("Contrasena:");
        lblP.setForeground(Color.WHITE);
        lblP.setFont(new Font("SansSerif", Font.PLAIN, 14));
        card.add(lblP, c);

        c.gridx = 1;
        campoContraseña = new JPasswordField(15);
        card.add(campoContraseña, c);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(15, 5, 5, 5);
        botonEntrar = new JButton("Entrar");
        botonEntrar.setActionCommand("Login:Entrar");
        card.add(botonEntrar, c);

        c.gridy = 4;
        c.insets = new Insets(5, 5, 5, 5);
        mensajeError = new JLabel(" ");
        mensajeError.setForeground(new Color(255, 100, 100));
        card.add(mensajeError, c);

        fondo.add(card);
        this.setContentPane(fondo);
        this.setVisible(true);
    }

    public String getNombreUsuario() { return campoUsuario.getText().trim(); }
    public String getContraseña() { return new String(campoContraseña.getPassword()); }
    public void mostrarError(String mensaje) { mensajeError.setText(mensaje); }

    public void setControlador(ActionListener c) {
        botonEntrar.addActionListener(c);
        campoContraseña.setActionCommand("Login:Entrar");
        campoContraseña.addActionListener(c);
    }
}