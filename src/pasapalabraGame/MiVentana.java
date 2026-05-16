package pasapalabraGame;

import java.awt.FlowLayout;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MiVentana extends JFrame {
    public Controlador controlador;
    public JButton miBoton; 
    public JButton miOtroBoton;
    public JTextField cajaTexto;
    public JTextArea areaConsola;
    
//inicio-----------------------------------------------------------------------------------------------
    public void mostrarDialogo(String titulo, String mensajeTexto, String nombre) {
        // Creamos el diálogo pasando 'this' (MiVentana) como el Frame padre, y true para que sea Modal
        Dialog miDialogo = new Dialog(this, titulo, true);
        miDialogo.setSize(900, 300);
        miDialogo.setLayout(new FlowLayout());
        

        // Componentes internos del diálogo
        Label texto = new Label(mensajeTexto);
        Button infantil = new Button("1. Infantil");
        Button Facil = new Button("2. Facil");
        Button Medio = new Button("3. Medio");
        Button Avanzado = new Button("4. Avanzado");
        Button PalabrasIconicas = new Button("5. Palabras iconicas");
        Label reglas = new Label("1. Las respuestas tendran que ponerse sin tildes | 2. Para saltar la pregunta escriba, Pasapalabra | 3. Pasatelo bien");
        Label Aviso = new Label("IMPORTANTE: No hace click fuera de la ventana del juego");
        
        miDialogo.add(texto);
        miDialogo.add(infantil);
        miDialogo.add(Facil);
        miDialogo.add(Medio);
        miDialogo.add(Avanzado);
        miDialogo.add(PalabrasIconicas);
        miDialogo.add(reglas);
        miDialogo.add(Aviso);

        
        //dificultad---------------------------------------------------------------------
        
        // Evento para cerrar el diálogo al pulsar el botón
        infantil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	miDialogo.dispose(); // Cierra y libera memoria del diálogo
            	pasapalabra pasa = new pasapalabra(1, nombre);
                
            }
        });
        // Evento para cerrar el diálogo al pulsar el botón
        Facil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	miDialogo.dispose(); // Cierra y libera memoria del diálogo
            	pasapalabra pasa = new pasapalabra(2, nombre);
     
            }
        });
        // Evento para cerrar el diálogo al pulsar el botón
        Medio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	miDialogo.dispose(); // Cierra y libera memoria del diálogo
            	pasapalabra pasa = new pasapalabra(3, nombre);
       
            }
        });
        // Evento para cerrar el diálogo al pulsar el botón
        Avanzado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	miDialogo.dispose(); // Cierra y libera memoria del diálogo
            	pasapalabra pasa = new pasapalabra(4, nombre);
     
            }
        });
        // Evento para cerrar el diálogo al pulsar el botón
        PalabrasIconicas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	miDialogo.dispose(); // Cierra y libera memoria del diálogo
            	pasapalabra pasa = new pasapalabra(5, nombre);
          
            }
        });

        // Evento para cerrar el diálogo si el usuario pulsa la 'X' de la esquina
        miDialogo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                miDialogo.dispose();
            }
        });

        // Centrar el diálogo respecto a esta ventana principal y mostrarlo
        miDialogo.setLocationRelativeTo(this);
        miDialogo.setVisible(true);
    }
    //----------------
    public void crearVista () {
        // 1. Configuraciones básicas y Layout
        this.setSize(500, 400); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout()); // <-- ¡Importante!

        // 2. Inicializar componentes
        cajaTexto = new JTextField(15);
        areaConsola = new JTextArea(10, 35);
        areaConsola.setEditable(false);
        areaConsola.setLineWrap(true);
        JScrollPane scrollConsola = new JScrollPane(areaConsola);

        // 3. Añadir al contenedor
        this.getContentPane().add(scrollConsola); 
        this.getContentPane().add(cajaTexto); 

        // 4. LA ÚLTIMA LÍNEA SIEMPRE
        this.setVisible(true); 
    }
}