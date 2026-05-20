package ModeloPP;

import ControlPP.Controlador;

public class pasapalabramain {
	
	public pasapalabramain(String Nombre) {
	
		// Creo la ventana
		MiVentana mainFrame=new MiVentana();
		// Creo el controlador pasando la ventana 
		Controlador mc=new Controlador(mainFrame);
		// Le asociamos el controlador a la ventana 
		mainFrame.controlador = mc; 
		
	
		mainFrame.mostrarDialogo("Dificultad", "Bienvenido a pasapalabra "+ Nombre+ " seleccione la difficultad del rosco : ", Nombre);
		
		
	}
}
