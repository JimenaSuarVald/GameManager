package ModeloPP;

import VistaPP.VentanaPasapalabra;
import Modelo.RutaArchivos;
import ModeloGen.JugadorActivo;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class pasapalabra {

    public static String[][] globalpreg1(String ruta) {
        String[][] preg1 = new String[26][3];
        try {
            Scanner sc = new Scanner(new File(RutaArchivos.resolver(ruta)));
            int fila = 0;
            while (sc.hasNextLine() && fila < 26) {
                String linea = sc.nextLine();
                String[] partes = linea.split(";");
                for (int i = 0; i <= 2; i++) {
                    preg1[fila][i] = partes[i];
                }
                fila++;
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return preg1;
    }

    public static String[][] basepartidas(String ruta2) {
        String[][] bas = new String[1000][2];
        try {
            Scanner sc = new Scanner(new File(RutaArchivos.resolver(ruta2)));
            int fila = 0;
            while (sc.hasNextLine() && fila < 1000) {
                String linea = sc.nextLine();
                String[] partes = linea.split(":");
                for (int i = 0; i <= 1; i++) {
                    bas[fila][i] = partes[i];
                }
                fila++;
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return bas;
    }

    private int dificultad;
    private String nombre;
    private int correctas;
    private int incorrectas;
    private int faltantes;

    public pasapalabra(int dificultad, String nombre) {
        this.dificultad = dificultad;
        this.nombre = nombre;

        String[][] preguntas1   = globalpreg1("Pasapalabra/ListasPP/Preguntas1.txt");
        String[][] preguntas11  = globalpreg1("Pasapalabra/ListasPP/Preguntas11.txt");
        String[][] preguntas12  = globalpreg1("Pasapalabra/ListasPP/Preguntas12.txt");
        String[][] preguntas2   = globalpreg1("Pasapalabra/ListasPP/Preguntas2.txt");
        String[][] preguntas21  = globalpreg1("Pasapalabra/ListasPP/Preguntas21.txt");
        String[][] preguntas22  = globalpreg1("Pasapalabra/ListasPP/Preguntas22.txt");
        String[][] preguntas3   = globalpreg1("Pasapalabra/ListasPP/Preguntas3.txt");
        String[][] preguntas31  = globalpreg1("Pasapalabra/ListasPP/Preguntas31.txt");
        String[][] preguntas32  = globalpreg1("Pasapalabra/ListasPP/Preguntas32.txt");
        String[][] preguntas4   = globalpreg1("Pasapalabra/ListasPP/Preguntas4.txt");
        String[][] preguntas41  = globalpreg1("Pasapalabra/ListasPP/Preguntas41.txt");
        String[][] preguntas42  = globalpreg1("Pasapalabra/ListasPP/Preguntas42.txt");
        String[][] preguntasicon = globalpreg1("Pasapalabra/ListasPP/palabrasiconicas.txt");

        String[] respuestas = new String[26];
        int faltantes = 0;
        int correctas = 0;
        int incorrectas = 0;
        int[] porresponder = new int[26];
        int num = 0;
        int x = 0;
        for (int i = 0; i < 26; i++) porresponder[i] = 0;
        int fin = 0;
        String preguntas[][] = new String[26][3];
        int a = 0;
        int b = 0;
        Random r = new Random();
        int n = r.nextInt(3);

        if (dificultad == 1) {
            while (b < 3) {
                int pregsel = n;
                if (pregsel == 0) { while (a < 26) { preguntas[a][b] = preguntas1[a][b]; a++; } b++; a = 0; }
                if (pregsel == 1) { while (a < 26) { preguntas[a][b] = preguntas11[a][b]; a++; } b++; a = 0; }
                if (pregsel == 2) { while (a < 26) { preguntas[a][b] = preguntas12[a][b]; a++; } b++; a = 0; }
            }
        }
        if (dificultad == 2) {
            while (b < 3) {
                int pregsel = n;
                if (pregsel == 0) { while (a < 26) { preguntas[a][b] = preguntas2[a][b]; a++; } b++; a = 0; }
                if (pregsel == 1) { while (a < 26) { preguntas[a][b] = preguntas21[a][b]; a++; } b++; a = 0; }
                if (pregsel == 2) { while (a < 26) { preguntas[a][b] = preguntas22[a][b]; a++; } b++; a = 0; }
            }
        }
        if (dificultad == 3) {
            while (b < 3) {
                int pregsel = n;
                if (pregsel == 0) { while (a < 26) { preguntas[a][b] = preguntas3[a][b]; a++; } b++; a = 0; }
                if (pregsel == 1) { while (a < 26) { preguntas[a][b] = preguntas31[a][b]; a++; } b++; a = 0; }
                if (pregsel == 2) { while (a < 26) { preguntas[a][b] = preguntas32[a][b]; a++; } b++; a = 0; }
            }
        }
        if (dificultad == 4) {
            while (b < 3) {
                int pregsel = n;
                if (pregsel == 0) { while (a < 26) { preguntas[a][b] = preguntas4[a][b]; a++; } b++; a = 0; }
                if (pregsel == 1) { while (a < 26) { preguntas[a][b] = preguntas41[a][b]; a++; } b++; a = 0; }
                if (pregsel == 2) { while (a < 26) { preguntas[a][b] = preguntas42[a][b]; a++; } b++; a = 0; }
            }
        }
        if (dificultad == 5) {
            while (b < 3) {
                while (a < 26) { preguntas[a][b] = preguntasicon[a][b]; a++; }
                b++; a = 0;
            }
        }

        String difi = "";
        if (dificultad == 1) difi = "Infantil";
        if (dificultad == 2) difi = "Facil";
        if (dificultad == 3) difi = "Media";
        if (dificultad == 4) difi = "Avanzada";
        if (dificultad == 5) difi = "palabras iconicas";
        System.out.println("difficultad seleccionada: " + difi);

        while (fin == 0) {
            while (num < 26) {
                if (porresponder[num] == 0) {
                    VentanaPasapalabra ventanaTurno = new VentanaPasapalabra(null, preguntas[num][1]);
                    String palabra = ventanaTurno.getRespuestaUsuario();
                    if (!palabra.isEmpty()) {
                        palabra = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
                    }
                    if (palabra.equals("Pasapalabra")) {
                        respuestas[num] = "Saltada";
                    } else {
                        if (palabra.equals(preguntas[num][2])) {
                            respuestas[num] = "Correcto";
                            porresponder[num] = 1;
                            correctas++;
                        } else {
                            respuestas[num] = "Incorrecto";
                            porresponder[num] = 1;
                            incorrectas++;
                        }
                    }
                }
                num++;
                if (num == 26) {
                    for (x = 0; x < 26; x++) {
                        if (porresponder[x] == 0) faltantes++;
                        if (x == 25 && faltantes != 0) {
                            VentanaPasapalabra v = new VentanaPasapalabra(null,
                                    "Sin responder: " + faltantes + " | Desea responder las faltantes? si/no");
                            String seguir = v.getRespuestaUsuario();
                            if (!seguir.isEmpty()) {
                                seguir = seguir.substring(0, 1).toUpperCase() + seguir.substring(1).toLowerCase();
                            }
                            if (seguir.equals("Si")) {
                                num = 0;
                                faltantes = 0;
                            } else {
                                VentanaPasapalabra v2 = new VentanaPasapalabra(null,
                                        "Fin de la partida | Aciertos: " + correctas
                                                + " | Fallos: " + incorrectas
                                                + " | Sin responder: " + faltantes
                                                + " | Desea jugar de nuevo? si/no");
                                String jugardnuevo = v2.getRespuestaUsuario();
                                new estadisticas(nombre, difi, correctas, incorrectas, faltantes);
                                JugadorActivo.registrarPartida(correctas * 10, faltantes == 0 && incorrectas == 0);
                                jugardnuevo = jugardnuevo.substring(0, 1).toUpperCase() + jugardnuevo.substring(1).toLowerCase();
                                if (jugardnuevo.equals("Si")) {
                                    new pasapalabramain(nombre);
                                }
                                fin = 1;
                            }
                        } else if (x == 25 && faltantes == 0) {
                            VentanaPasapalabra v2 = new VentanaPasapalabra(null,
                                    "Todas respondidas, fin de la partida | Aciertos: " + correctas
                                            + " | Fallos: " + incorrectas
                                            + " | Desea jugar de nuevo? si/no");
                            String jugardnuevo = v2.getRespuestaUsuario();
                            new estadisticas(nombre, difi, correctas, incorrectas, faltantes);
                            JugadorActivo.registrarPartida(correctas * 10, incorrectas == 0);
                            jugardnuevo = jugardnuevo.substring(0, 1).toUpperCase() + jugardnuevo.substring(1).toLowerCase();
                            if (jugardnuevo.equals("Si")) {
                                new pasapalabramain(nombre);
                            }
                            fin = 1;
                        }
                    }
                }
            }
        }
    }
}