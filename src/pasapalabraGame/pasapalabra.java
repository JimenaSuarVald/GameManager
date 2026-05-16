package pasapalabraGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class pasapalabra{
	public static String[][] globalpreg1(String ruta) {//creo una clase publica para acceder a ella mas tarde
    	String[][] preg1 = new String[26][3];//creo una matriz que almacenara los datos del documento
    	try {
    		Scanner sc = new Scanner(new File(ruta));//usando scanner y file leo el documento
    		int fila = 0;//para la matriz de arriba
    		
    		while(sc.hasNextLine() && fila < 26) {//mientras el documento tenga filas y la variable filas no exceda 26(el numero de preguntas)
    			String linea = sc.nextLine();
    			String[] partes = linea.split(";");
    			for(int i = 0;i <=2;i++) {
    				preg1[fila][i] = partes[i];
    			}
    			fila++;
    		}
    		sc.close();

    	}catch(Exception e) {
    		System.out.println("Error: "+e.getMessage());
    	}
    	return preg1;
    }
	 public static String[][] basepartidas(String ruta2) {
	    	String[][] bas = new String[9999999][2];
	    	try {
	    		Scanner sc = new Scanner(new File(ruta2));
	    		int fila = 0;
	    		
	    		while(sc.hasNextLine() && fila < 9999999) {
	    			String linea = sc.nextLine();
	    			String[] partes = linea.split(":");
	    			for(int i = 0;i <=1;i++) {
	    				bas[fila][i] = partes[i];
	    			}
	    			fila++;
	    		}
	    		sc.close();

	    	}catch(Exception e) {
	    		System.out.println("Error: "+e.getMessage());
	    	}
	    	return bas;
	    }
	 private int dificultad;
	 private String nombre;
	 private int correctas;
	 private int incorrectas;
	 private int faltantes;
	 
	public pasapalabra(int dificultad, String nombre) {

		//-------------------------------
		this.dificultad = dificultad;
		this.nombre = nombre;
		String[][] preguntas1 = globalpreg1("Preguntas1.txt");
		String[][] preguntas11 = globalpreg1("Preguntas11.txt");
		String[][] preguntas12 = globalpreg1("Preguntas12.txt");
		String[][] preguntas2 = globalpreg1("Preguntas2.txt");
		String[][] preguntas21 = globalpreg1("Preguntas21.txt");
		String[][] preguntas22 = globalpreg1("Preguntas22.txt");
		String[][] preguntas3 = globalpreg1("Preguntas3.txt");
		String[][] preguntas31 = globalpreg1("Preguntas31.txt");
		String[][] preguntas32 = globalpreg1("Preguntas32.txt");
		String[][] preguntas4 = globalpreg1("Preguntas4.txt");
		String[][] preguntas41 = globalpreg1("Preguntas41.txt");
		String[][] preguntas42 = globalpreg1("Preguntas42.txt");
		String[][] preguntasicon = globalpreg1("palabrasiconicas.txt");
		String[][] partidas = basepartidas("partidasusuarios.txt");
		Scanner respuesta = new Scanner(System.in);
		String[] respuestas = new String[26];//En este array se almacena si la respuesta es correcta, incorrecta o si no esta respondida
		int faltantes = 0;//sin responder
		int correctas = 0;//preguntas correctas
		int incorrectas = 0;//preguntas incorrectas
		int [] porresponder = new int[26];//preguntas no respondidas, 0 = no respondida, 1 = si respondida
		int num = 0;//Fila para coger pregunta y respuesta
		int x = 0;//variable para la comprobacion que se usa al final de las preguntas para ver si el usuario continua o no
		for(int i=0;i<26;i++) {//pone todas a 0, que es no respondidas
			porresponder[i] = 0;
		}
		int fin = 0;//0=seguir y 1=acabar partida
		//para escoger un documento al azar
		String preguntas[][] = new String[26][3];//aqui se alamacenaran las pregutnas, esta es una matriz generica usada en todo el codigo de preguntas
		int a = 0;//fila matriz preguntas
		int b = 0;//columna matriz preguntas
		Random r = new Random();//variable r, como un scanner
		int n = r.nextInt(3);//random del 0 al 2   
		if(dificultad == 1) {
			while (b < 3) {
				int pregsel = n;
				if(pregsel == 0) {//copia la matriz de preguntas seleccionada a una matriz generica para ser usada a la hora de preguntar
					while(a< 26) {
						preguntas [a][b] = preguntas1[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
				if(pregsel == 1) {
					while(a< 26) {
						preguntas [a][b] = preguntas11[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
				if(pregsel == 2) {
					while(a< 26) {
						preguntas [a][b] = preguntas12[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
			}
		}
		if(dificultad == 2) {
			while (b < 3) {
				int pregsel = n;
				if(pregsel == 0) {
					while(a< 26) {
						preguntas [a][b] = preguntas2[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
				if(pregsel == 1) {
					while(a< 26) {
						preguntas [a][b] = preguntas21[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
				if(pregsel == 2) {
					while(a< 26) {
						preguntas [a][b] = preguntas22[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
			}
		}
		if(dificultad == 3) {
			while (b < 3) {
				int pregsel = n;
				if(pregsel == 0) {
					while(a< 26) {
						preguntas [a][b] = preguntas3[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
				if(pregsel == 1) {
					while(a< 26) {
						preguntas [a][b] = preguntas31[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
				if(pregsel == 2) {
					while(a< 26) {
						preguntas [a][b] = preguntas32[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
			}
		}
		if(dificultad == 4) {
			while (b < 3) {
				int pregsel = n;
				if(pregsel == 0) {
					while(a< 26) {
						preguntas [a][b] = preguntas4[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
				if(pregsel == 1) {
					while(a< 26) {
						preguntas [a][b] = preguntas41[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
				if(pregsel == 2) {
					while(a< 26) {
						preguntas [a][b] = preguntas42[a][b];
						a++;
					}
					b++;
					a = 0;	
				}
			}
		}
		if(dificultad == 5) {
			while (b < 3) {
				while(a< 26) {
					preguntas [a][b] = preguntasicon[a][b];
					a++;
				}
				b++;
				a = 0;
			}
		}
		
		
		String difi = "";//este dato escribira en los datos de la partida la dificultad selecionada por el usuario
		System.out.println("=========================");
		if(dificultad == 1) {//una vez la dificultad se ha seleccionado se ejecuta este if
			System.out.println("difficultad seleccionada: Infantil");	
			difi = "Infantil";
		}
		if(dificultad == 2) {//una vez la dificultad se ha seleccionado se ejecuta este if
			System.out.println("difficultad seleccionada: Facil");	
			difi = "Facil";
		}
		if(dificultad == 3) {//una vez la dificultad se ha seleccionado se ejecuta este if
			System.out.println("difficultad seleccionada: Media");	
			difi = "Media";
		}
		if(dificultad == 4) {//una vez la dificultad se ha seleccionado se ejecuta este if
			System.out.println("difficultad seleccionada: Avanzada");	
			difi = "Avanzada";
		}
		if(dificultad == 5) {//una vez la dificultad se ha seleccionado se ejecuta este if
			System.out.println("difficultad seleccionada: palabras iconicas");	
			difi = "palabras iconicas";
		}
		while(fin == 0) {//para acabar
			while(num < 26) {//para recorrer el array con las preguntas
				if(porresponder[num] == 0) {//comprueba que esa pregunta esta sin responder
					System.out.println("=========================");
					System.out.println(preguntas[num][1]);//escupe la pregunta
					VentanaPasapalabra ventanaTurno = new VentanaPasapalabra(null, preguntas[num][1]);
					//String palabra = respuesta.next();
					String palabra = ventanaTurno.getRespuestaUsuario();
					if (!palabra.isEmpty()) {//hace mayuscula la primera letra de la respuesta
					    palabra = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();//coge el primer caracter y lo cambia a mayuscula
					}
					if(palabra.equals("Pasapalabra")) {//cuando el usuario responda pasapalabra saltara la pregunta
						System.out.println("Salatada");
						respuestas [num] = "Saltada";//marca la pregunta en el array de respuestas como saltada
						System.out.println("=========================");
					}else {
						if(palabra.equals(preguntas[num][2])) {
							System.out.println("Correcto!");
							respuestas [num] = "Correcto";//mete en un array que esa es correcta
							porresponder[num] = 1;//marca la pregunta en el array porresponder como hecha para que no se repita
							correctas++;
							System.out.println("=========================");
						} else {
							System.out.println("Incorrecto!");
							respuestas [num] = "Incorrecto";//mete en un array que esa es incorrecta
							porresponder[num] = 1;//marca la pregunta en el array porresponder como hecha para que no se repita
							incorrectas++;
							System.out.println("=========================");
						}
					 }
				 }
				num++;
				//comprobacion para ver si el usuario necesita repetir preguntas o no
				if(num == 26) {
					for(x = 0;x<26;x++) {
						if(porresponder[x] == 0) {
							faltantes++;//suma a faltantes para ver si el usuario quiere seguir intentandolo
						}
						if(x == 25 && faltantes != 0) {
							VentanaPasapalabra ventanaTurno = new VentanaPasapalabra(null, "Sin responder: "+faltantes+" | Desea responder las faltantes?si/no");
							System.out.println("Desea responder las faltantes?si/no");//con esto se pregunta si quiere responder las faltantes
							String seguir = ventanaTurno.getRespuestaUsuario();
							System.out.println("=========================");
							if (!seguir.isEmpty()) {//hace mayuscula la primera letra de la respuesta
							    seguir = seguir.substring(0, 1).toUpperCase() + seguir.substring(1).toLowerCase();
							}
							if(seguir.equals("Si")) {//acaba la partida si el usuario responde algo que no sea si
								num = 0;//pone num a 0 para reiniciar el bucle while que hace las preguntas
								faltantes = 0;//reinicia la variable faltantes
							}else {
								VentanaPasapalabra ventanaTurno2 = new VentanaPasapalabra(null,"Fin de la partida|Aciertos: "+ correctas + " |Fallos: "+ incorrectas + " |Sin responder: "+ faltantes +  " |Desea jugar de nuevo?si/no");
								String jugardnuevo = ventanaTurno2.getRespuestaUsuario();
								System.out.println("=========================");
								System.out.println("fin de la partida");
								System.out.println("Correctas: "+correctas);//dice cuantas hay bien
								System.out.println("Incorrectas: "+incorrectas);//dice cuantas hay mal
								System.out.println("Sin Responder: "+faltantes);//dice cuantas hay mal
								System.out.println("=========================");
								estadisticas estat = new estadisticas(nombre,difi, correctas, incorrectas, faltantes );
								jugardnuevo = jugardnuevo.substring(0, 1).toUpperCase() + jugardnuevo.substring(1).toLowerCase();
								if(jugardnuevo.equals("Si")) {
									pasapalabramain pasnuevo = new pasapalabramain(nombre);
								}
							}
						}else if(x == 25 && faltantes == 0) {//si no hay faltantes la partida acaba automaticamente con esta comprobacion
							VentanaPasapalabra ventanaTurno2 = new VentanaPasapalabra(null,"Todas respondidas, fin de la partida |Aciertos:  "+ correctas + " |Fallos: "+ incorrectas + " |Desea jugar de nuevo?si/no");
							String jugardnuevo = ventanaTurno2.getRespuestaUsuario();
							System.out.println("=========================");
							System.out.println("Todas respondidas, fin de la partida");
							System.out.println("Correctas: "+correctas);//dice cuantas hay bien
							System.out.println("Incorrectas: "+incorrectas);//dice cuantas hay mal
							System.out.println("=========================");
							estadisticas estat = new estadisticas(nombre,difi, correctas, incorrectas, faltantes );
							jugardnuevo = jugardnuevo.substring(0, 1).toUpperCase() + jugardnuevo.substring(1).toLowerCase();
							if(jugardnuevo.equals("Si")) {
								pasapalabramain pasnuevo = new pasapalabramain(nombre);
							}
						}
					}
				}
			}
		
		}
	}//fin del public
}
