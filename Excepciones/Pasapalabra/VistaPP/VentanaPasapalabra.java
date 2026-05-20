package VistaPP;

import ControlPP.ControladorPasapalabra;

import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class VentanaPasapalabra extends JDialog {

    private JTextField cajaTexto;
    private Label etiquetaTexto;
    private String respuestaUsuario = ""; // Aquí guardaremos el resultado final

    // El constructor ahora pide el JFrame principal
    public VentanaPasapalabra(JFrame padre, String pregunta) {
        // 'padre' es la ventana de fondo, 'pregunta' es el título, 'true' lo hace modal
        super(padre, "Turno de Pasapalabra", true); 
        
        this.setSize(600, 180);
        this.getContentPane().setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Evita que fuercen el cierre sin responder

        // Componentes
        etiquetaTexto = new Label(pregunta);
        cajaTexto = new JTextField(20);

        // Conectamos el controlador encargado de capturar el ENTER
        ControladorPasapalabra controlador = new ControladorPasapalabra(this);
        cajaTexto.addActionListener(controlador);

        this.getContentPane().add(etiquetaTexto);
        this.getContentPane().add(cajaTexto);

        this.setLocationRelativeTo(padre); // Se centra respecto al juego
        // el código que invoque este constructor se parará AQUÍ
        // hasta que el diálogo haga .dispose()
        this.setVisible(true); 
    }

    // Métodos para que el controlador y tu juego interactúen
    public JTextField getCajaTexto() {
        return this.cajaTexto;
    }

    public void setRespuestaUsuario(String respuesta) {
        this.respuestaUsuario = respuesta;
    }

    public String getRespuestaUsuario() {
        return this.respuestaUsuario;
    }
}