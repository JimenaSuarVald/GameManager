package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GestorArchivos {

    String rutaItems = "Juego/Listas/Objetos";
    String rutaEnemigos = "Juego/Listas/Enemigos";
    String rutaHabilidades = "Juego/Listas/Habilidades";
    String rutaClases = "Juego/Listas/Clases";

    public Item[] cargarItems(String rutaItems) {
        Item[] catalogoItems = new Item[16];
        int contador = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(RutaArchivos.resolver(rutaItems)));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    catalogoItems[contador++] = new Item(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3]);
                }
            }
            System.out.println("¡Se han cargado " + contador + " objetos correctamente!");
            lector.close();
        } catch (IOException e) {
            System.out.println("¡Error al intentar leer el archivo objetos!");
            e.printStackTrace();
        }
        return catalogoItems;
    }

    public ClaseJugador[] cargarClases(String rutaClases) {
        ClaseJugador[] catalogoClases = new ClaseJugador[3];
        int contador = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(RutaArchivos.resolver(rutaClases)));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    String[] habilidades = {partes[2], partes[3], partes[4]};
                    catalogoClases[contador++] = new ClaseJugador(partes[0], partes[1], habilidades);
                }
            }
            System.out.println("¡Se han cargado " + contador + " clases correctamente!");
            lector.close();
        } catch (IOException e) {
            System.out.println("¡Error al intentar leer el archivo clases!");
            e.printStackTrace();
        }
        return catalogoClases;
    }

    public Habilidades[] cargarHabilidades(String rutaHabilidades) {
        Habilidades[] catalogoHabilidades = new Habilidades[10];
        int contador = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(RutaArchivos.resolver(rutaHabilidades)));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    catalogoHabilidades[contador++] = new Habilidades(partes[0], partes[2], partes[3], Integer.parseInt(partes[1]));
                }
            }
            System.out.println("¡Se han cargado " + contador + " habilidades correctamente!");
            lector.close();
        } catch (IOException e) {
            System.out.println("¡Error al intentar leer el archivo habilidades!");
            e.printStackTrace();
        }
        return catalogoHabilidades;
    }

    public Enemigos[] cargarEnemigos(String rutaEnemigos) {
        Enemigos[] catalogoEnemigos = new Enemigos[100];
        int contador = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(RutaArchivos.resolver(rutaEnemigos)));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    catalogoEnemigos[contador++] = new Enemigos(partes[0], Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), partes[3]);
                }
            }
            System.out.println("¡Se han cargado " + contador + " enemigos correctamente!");
            lector.close();
        } catch (IOException e) {
            System.out.println("¡Error al intentar leer el archivo enemigos!");
            e.printStackTrace();
        }
        return catalogoEnemigos;
    }
}