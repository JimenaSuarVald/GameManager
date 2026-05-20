package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivos {

    // Rutas de los archivos de texto
    private static final String RUTA_USUARIOS  = "datos/usuarios.txt";
    private static final String RUTA_HISTORIAL = "datos/historial.txt";

    // ---------------------------------------------------------------
    // GUARDAR USUARIOS
    // ---------------------------------------------------------------
    public static void guardarUsuarios(ArrayList<Persona> lista) {

        // Creamos la carpeta "datos" si no existe todavia
        new File("datos").mkdirs();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_USUARIOS));

            for (Persona p : lista) {

                if (p instanceof Administrador) {
                    // Formato: ADMIN,nombre,contraseña
                    bw.write("ADMIN," + p.getNombre() + "," + p.getContraseña());

                } else if (p instanceof Jugador) {
                    Jugador j = (Jugador) p;
                    // Formato: JUGADOR,nombre,contraseña,accesos,partidas,puntuacionMaxima
                    bw.write("JUGADOR," + j.getNombre() + "," + j.getContraseña() + ","
                            + j.getContadorAccesos() + "," + j.getPartidasJugadas() + ","
                            + j.getPuntuacionMaxima());
                }

                bw.newLine(); // salto de linea entre cada usuario
            }

            bw.close();
            System.out.println("Usuarios guardados en .txt correctamente.");

        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // CARGAR USUARIOS
    // ---------------------------------------------------------------
    public static ArrayList<Persona> cargarUsuarios() {

        ArrayList<Persona> lista = new ArrayList<>();
        File archivo = new File(RUTA_USUARIOS);

        // Si el archivo no existe todavia devolvemos lista vacia sin error
        if (!archivo.exists()) {
            System.out.println("No hay usuarios guardados, empezando desde cero.");
            return lista;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTA_USUARIOS));
            String linea;

            while ((linea = br.readLine()) != null) {

                // Separamos cada linea por comas
                String[] partes = linea.split(",");

                if (partes[0].equals("ADMIN")) {
                    // partes[1] = nombre, partes[2] = contraseña
                    lista.add(new Administrador(partes[1], partes[2]));

                } else if (partes[0].equals("JUGADOR")) {
                    // partes[1]=nombre [2]=contraseña [3]=accesos [4]=partidas [5]=puntuacion
                    Jugador j = new Jugador(partes[1], partes[2]);
                    j.setContadorAccesos(Integer.parseInt(partes[3]));
                    j.setPartidasJugadas(Integer.parseInt(partes[4]));
                    j.setPuntuacionMaxima(Integer.parseInt(partes[5]));
                    lista.add(j);
                }
            }

            br.close();
            System.out.println("Usuarios cargados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }

        return lista;
    }

    // ---------------------------------------------------------------
    // GUARDAR UNA PARTIDA EN EL HISTORIAL (para que el admin lo vea)
    // ---------------------------------------------------------------
    public static void guardarPartidaEnHistorial(String nombreJugador, String juego,
                                                  String fecha, int puntuacion) {
        new File("datos").mkdirs();

        try {
            // true = append, añade al final sin borrar lo que ya habia
            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_HISTORIAL, true));
            bw.write(nombreJugador + "," + juego + "," + fecha + "," + puntuacion);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.out.println("Error al guardar historial: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // CARGAR HISTORIAL (devuelve todas las lineas para que el admin las muestre)
    // ---------------------------------------------------------------
    public static ArrayList<String> cargarHistorial() {

        ArrayList<String> historial = new ArrayList<>();
        File archivo = new File(RUTA_HISTORIAL);

        if (!archivo.exists()) {
            return historial; // vacio pero sin error
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTA_HISTORIAL));
            String linea;

            while ((linea = br.readLine()) != null) {
                historial.add(linea);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error al cargar historial: " + e.getMessage());
        }

        return historial;
    }
}