package Vista;

import Modelo.RutaArchivos;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelFondo extends JPanel {
    private Image fondo;

    public PanelFondo() {
        super();
    }

    public PanelFondo(LayoutManager layout) {
        super(layout);
    }

    public void cargarFondo(String rutaRelativa) {
        if (rutaRelativa == null || rutaRelativa.isEmpty()) {
            fondo = null;
            repaint();
            return;
        }
        String ruta = RutaArchivos.resolver(rutaRelativa);
        if (new File(ruta).exists()) {
            fondo = new ImageIcon(ruta).getImage();
        } else {
            System.out.println("Fondo no encontrado: " + ruta);
            fondo = null;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}