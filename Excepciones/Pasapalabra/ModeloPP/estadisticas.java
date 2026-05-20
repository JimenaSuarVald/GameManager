package ModeloPP;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import Modelo.RutaArchivos;

public class estadisticas {
	String Usuario;
	String difi;
	int correctas;
	int incorrectas;
	int faltantes;
	
	public estadisticas(String Usuario,String difi, int correctas, int incorrectas, int faltantes) {
		this.Usuario = Usuario;
		this.difi = difi;
		this.correctas = correctas;
		this.incorrectas = incorrectas;
		this.faltantes = faltantes;
		System.out.println("Guardando Partida");
		try {
            FileWriter fw = new FileWriter(RutaArchivos.resolver("Pasapalabra/ListasPP/partidasusuarios.txt"), true); // append
	        PrintWriter pw = new PrintWriter(fw);
	        pw.println("----------------:----------------");
		    pw.println("PARTIDA USUARIO: Pasapalabra");
		    pw.println("dificultad:"+difi);
		    pw.println("Nombre usuario:"+Usuario);
		    pw.println("aciertos partida:"+correctas);
		    pw.println("fallos partida:"+incorrectas);
		    pw.println("pasapalabra partida:"+faltantes);
		    pw.println("----------------:----------------");
	        pw.close();
	    } catch (IOException e) {
	        System.out.println("Error escribiendo");
	    }
	 }
}
