package Vista;

import Modelo.Habilidades;
import Modelo.RutaArchivos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VistaObjectBound extends JFrame {
    private PanelEscenario panelJuego;
    private JPanel panelTop;
    private JPanel panelEnemigos;
    private JPanel[] slotsEnemigos;
    private JPanel panelTexto;
    private JPanel panelInferior;
    private JPanel panelBotonesCombate;
    private JPanel panelBotonesNarrativa;
    private JButton AtacarButton;
    private JButton DefenderButton;
    private JButton HabilidadButton;
    private JButton ItemButton;
    private JButton MenuButton;
    private JButton ContinuarButton;
    private JButton MenuNarrativaButton;
    private JTextArea areaTextoLog;
    private JLabel etiquetaVidaJugador;
    private JLabel[] iconosEnemigos;
    private JLabel[] etiquetasEnemigos;

    public VistaObjectBound() {
        panelJuego = new PanelEscenario();
        panelTexto = new JPanel();
        panelInferior = new JPanel();
        panelBotonesCombate = new JPanel();
        panelBotonesNarrativa = new JPanel();
        AtacarButton = new JButton("Atacar");
        DefenderButton = new JButton("Defender");
        HabilidadButton = new JButton("Habilidad");
        ItemButton = new JButton("Item");
        MenuButton = new JButton("Menu");
        ContinuarButton = new JButton("Continuar");
        MenuNarrativaButton = new JButton("Menu");

        areaTextoLog = new JTextArea(8, 30);
        areaTextoLog.setEditable(false);
        areaTextoLog.setLineWrap(true);
        areaTextoLog.setWrapStyleWord(true);
        JScrollPane scrollTexto = new JScrollPane(areaTextoLog);

        // Panel escenario (fondo)
        panelJuego.setLayout(new BorderLayout(10, 10));
        panelJuego.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Arriba: vida del jugador
        panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.setOpaque(false);
        etiquetaVidaJugador = new JLabel("Tu Vida: 100");
        etiquetaVidaJugador.setForeground(Color.WHITE);
        etiquetaVidaJugador.setBackground(Color.BLACK);
        etiquetaVidaJugador.setOpaque(true);
        etiquetaVidaJugador.setFont(new Font("SansSerif", Font.BOLD, 14));
        etiquetaVidaJugador.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        panelTop.add(etiquetaVidaJugador);
        panelJuego.add(panelTop, BorderLayout.NORTH);

        // Centro: slots de enemigos
        panelEnemigos = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelEnemigos.setOpaque(false);
        panelEnemigos.setPreferredSize(new Dimension(450, 180));
        panelEnemigos.setMaximumSize(new Dimension(450, 180));

        iconosEnemigos = new JLabel[3];
        etiquetasEnemigos = new JLabel[3];
        slotsEnemigos = new JPanel[3];

        for (int i = 0; i < 3; i++) {
            JPanel slot = new JPanel(new BorderLayout(0, 5));
            slot.setOpaque(false);
            slot.setPreferredSize(new Dimension(120, 160));

            iconosEnemigos[i] = new JLabel("", JLabel.CENTER);
            slot.add(iconosEnemigos[i], BorderLayout.CENTER);

            etiquetasEnemigos[i] = new JLabel(" ", JLabel.CENTER);
            etiquetasEnemigos[i].setForeground(Color.WHITE);
            etiquetasEnemigos[i].setBackground(Color.BLACK);
            etiquetasEnemigos[i].setOpaque(true);
            etiquetasEnemigos[i].setFont(new Font("SansSerif", Font.BOLD, 12));
            etiquetasEnemigos[i].setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            slot.add(etiquetasEnemigos[i], BorderLayout.SOUTH);

            slot.setVisible(false);
            slotsEnemigos[i] = slot;
            panelEnemigos.add(slot);
        }

        JPanel wrapperEnemigos = new JPanel(new GridBagLayout());
        wrapperEnemigos.setOpaque(false);
        wrapperEnemigos.add(panelEnemigos);
        panelJuego.add(wrapperEnemigos, BorderLayout.CENTER);

        panelTop.setVisible(false);
        panelEnemigos.setVisible(false);

        this.setTitle("ObjectBound");
        this.setSize(600, 600);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panelBotonesCombate.setLayout(new GridLayout(5, 1, 5, 5));
        panelBotonesCombate.add(AtacarButton);
        panelBotonesCombate.add(DefenderButton);
        panelBotonesCombate.add(HabilidadButton);
        panelBotonesCombate.add(ItemButton);
        panelBotonesCombate.add(MenuButton);

        panelBotonesNarrativa.setLayout(new GridLayout(2, 1, 5, 5));
        panelBotonesNarrativa.add(ContinuarButton);
        panelBotonesNarrativa.add(MenuNarrativaButton);

        panelTexto.setLayout(new BorderLayout());
        panelTexto.add(scrollTexto, BorderLayout.CENTER);

        panelInferior.setLayout(new BorderLayout());
        panelInferior.add(panelTexto, BorderLayout.CENTER);
        panelInferior.add(panelBotonesCombate, BorderLayout.EAST);

        this.add(panelJuego, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void setControlador(ActionListener c) {
        AtacarButton.addActionListener(c);
        DefenderButton.addActionListener(c);
        HabilidadButton.addActionListener(c);
        ItemButton.addActionListener(c);
        MenuButton.addActionListener(c);
        ContinuarButton.addActionListener(c);
        MenuNarrativaButton.addActionListener(c);
    }

    public void modoNarrativa(String texto) {
        areaTextoLog.setText(texto);
        panelJuego.setVisible(true);
        panelTop.setVisible(false);
        panelEnemigos.setVisible(false);
        panelInferior.remove(panelBotonesCombate);
        panelInferior.add(panelBotonesNarrativa, BorderLayout.EAST);
        panelInferior.revalidate();
        panelInferior.repaint();
    }

    public void modoCombate() {
        areaTextoLog.setText("");
        panelJuego.setVisible(true);
        panelTop.setVisible(true);
        panelEnemigos.setVisible(true);
        panelInferior.remove(panelBotonesNarrativa);
        panelInferior.add(panelBotonesCombate, BorderLayout.EAST);
        panelInferior.revalidate();
        panelInferior.repaint();
    }

    public void cargarEscenario(String nombre) {
        panelJuego.cargarFondo(nombre);
    }

    public void cargarEnemigos(String[] nombresEnemigos) {
        for (int i = 0; i < iconosEnemigos.length; i++) {
            if (i < nombresEnemigos.length) {
                ImageIcon icono = cargarIcono(
                        "Juego/Recursos/Enemigos/" + nombresEnemigos[i].trim() + ".png", 120, 120);
                iconosEnemigos[i].setIcon(icono);
                if (icono == null) {
                    iconosEnemigos[i].setText(nombresEnemigos[i].trim());
                    iconosEnemigos[i].setForeground(Color.WHITE);
                    iconosEnemigos[i].setFont(new Font("SansSerif", Font.BOLD, 14));
                } else {
                    iconosEnemigos[i].setText("");
                }
                slotsEnemigos[i].setVisible(true);
            } else {
                slotsEnemigos[i].setVisible(false);
            }
        }
        panelEnemigos.revalidate();
        panelEnemigos.repaint();
    }

    private ImageIcon cargarIcono(String ruta, int w, int h) {
        String rutaFinal = RutaArchivos.resolver(ruta);
        File f = new File(rutaFinal);
        if (!f.exists()) {
            System.out.println("Imagen no encontrada: " + rutaFinal);
            return null;
        }
        Image img = new ImageIcon(rutaFinal).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public void actualizarVidaJugador(int vida) {
        etiquetaVidaJugador.setText("Tu Vida: " + vida);
    }

    public void actualizarVidaEnemigo(int indice, String nombre, int vida) {
        if (indice >= 0 && indice < etiquetasEnemigos.length) {
            if (vida <= 0) {
                slotsEnemigos[indice].setVisible(false);
                panelEnemigos.revalidate();
                panelEnemigos.repaint();
            } else {
                etiquetasEnemigos[indice].setText(nombre + "  HP: " + vida);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        areaTextoLog.append(mensaje + "\n");
        areaTextoLog.setCaretPosition(areaTextoLog.getDocument().getLength());
    }

    public int seleccionarEnemigoObjetivo(String[] nombresEnemigos) {
        String seleccion = (String) JOptionPane.showInputDialog(
                this, "A que enemigo deseas atacar?", "Seleccionar Objetivo",
                JOptionPane.QUESTION_MESSAGE, null, nombresEnemigos, nombresEnemigos[0]);
        if (seleccion != null) {
            for (int i = 0; i < nombresEnemigos.length; i++) {
                if (seleccion.equals(nombresEnemigos[i])) return i;
            }
        }
        return -1;
    }

    public String seleccionarItemMochila(String[] nombresItems) {
        if (nombresItems.length == 0) {
            JOptionPane.showMessageDialog(this, "Tu mochila esta vacia.");
            return null;
        }
        return (String) JOptionPane.showInputDialog(
                this, "Selecciona un objeto:", "Mochila",
                JOptionPane.QUESTION_MESSAGE, null, nombresItems, nombresItems[0]);
    }

    public String seleccionarHabilidad(Habilidades[] habilidades) {
        if (habilidades == null || habilidades.length == 0) {
            JOptionPane.showMessageDialog(this, "No tienes habilidades.");
            return null;
        }
        int disponibles = 0;
        for (int i = 0; i < habilidades.length; i++) {
            if (habilidades[i] != null) disponibles++;
        }
        if (disponibles == 0) {
            JOptionPane.showMessageDialog(this, "Aún no tienes habilidades disponibles.");
            return null;
        }
        String[] opciones = new String[disponibles];
        int j = 0;
        for (int i = 0; i < habilidades.length; i++) {
            if (habilidades[i] != null) {
                opciones[j++] = habilidades[i].getNombre() + " — " + habilidades[i].getDescripcion();
            }
        }
        String seleccion = (String) JOptionPane.showInputDialog(
                this, "Selecciona una habilidad:", "Habilidades",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (seleccion == null) return null;
        int idx = seleccion.indexOf(" — ");
        if (idx >= 0) return seleccion.substring(0, idx);
        return seleccion;
    }
}