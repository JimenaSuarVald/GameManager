package Vista;

import Modelo.RutaArchivos;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelEscenario extends JPanel {
    private Image fondo;

    public PanelEscenario() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(500, 250));
    }

    public void cargarFondo(String nombreEscenario) {
        if (nombreEscenario == null || nombreEscenario.isEmpty()) {
            fondo = null;
            repaint();
            return;
        }
        String ruta = RutaArchivos.resolver("Juego/Recursos/Escenarios/" + nombreEscenario + ".png");
        if (new File(ruta).exists()) {
            fondo = new ImageIcon(ruta).getImage();
        } else {
            System.out.println("Escenario no encontrado: " + ruta);
            fondo = null;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}