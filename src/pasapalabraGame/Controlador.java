package pasapalabraGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
	MiVentana miVentana;
	public Controlador (MiVentana win) { 
		miVentana =win;
		}
	// método al quie se llamará al hacer click
	public void actionPerformed(ActionEvent e) { 
	if (e.getSource() == miVentana.miBoton) {
	System.out.println("Ocurrió un Evento en el botón");
	} else {
	System.out.println("Ocurrió un Evento en el otro botón");
	}
	}

}
