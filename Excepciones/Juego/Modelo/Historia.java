package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Historia {
    public Capitulo[] cargarHistoria(String ruta) {
        Capitulo[] capitulos = new Capitulo[200];
        int contador = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(RutaArchivos.resolver(ruta)));
            String linea;
            while ((linea = lector.readLine()) != null && contador < capitulos.length) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split("\\|", -1);
                String texto = partes[0];
                String[] enemigos = null;
                if (partes.length >= 2 && !partes[1].trim().isEmpty()) {
                    enemigos = partes[1].split(",");
                }
                String[] recompensas = null;
                if (partes.length >= 3 && !partes[2].trim().isEmpty()) {
                    recompensas = partes[2].split(",");
                }
                String escenario = null;
                if (partes.length >= 4 && !partes[3].trim().isEmpty()) {
                    escenario = partes[3].trim();
                }
                capitulos[contador] = new Capitulo(texto, enemigos, recompensas, escenario);
                contador++;
            }
            System.out.println("Se han cargado " + contador + " capitulos.");
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo Historia.");
            e.printStackTrace();
        }
        return capitulos;
    }
}