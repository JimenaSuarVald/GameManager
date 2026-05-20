package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GestorPartidas {
    private Item[] catalogoItems;
    private ClaseJugador[] catalogoClases;

    public GestorPartidas(Item[] catalogoItems, ClaseJugador[] catalogoClases) {
        this.catalogoItems = catalogoItems;
        this.catalogoClases = catalogoClases;
    }

    private String rutaSave(Usuario u) {
        return RutaArchivos.resolver("Juego/Saves/" + u.getNombre() + ".txt");
    }

    private String rutaCarpetaSaves() {
        return RutaArchivos.resolver("Juego/Saves");
    }

    public boolean existePartida(Usuario u) {
        return new File(rutaSave(u)).exists();
    }

    public void guardar(LogicaJuego partida) {
        try {
            File dir = new File(rutaCarpetaSaves());
            if (!dir.exists()) dir.mkdirs();

            PrintWriter pw = new PrintWriter(new FileWriter(rutaSave(partida.getUsuario())));
            Jugador j = partida.getJugador();

            pw.println("JUGADOR;" + j.getNombre() + ";" + j.getVida());
            pw.println("CLASE;" + j.getClaseJugador().getNombre());
            pw.println("ARMA;" + j.getArma().getNombre());

            StringBuilder sb = new StringBuilder("MOCHILA;");
            Mochila m = j.getMochila();
            Item[] eq = m.getEquipaje();
            for (int i = 0; i < m.getCantidadItems(); i++) {
                if (i > 0) sb.append(",");
                sb.append(eq[i].getNombre());
            }
            pw.println(sb.toString());
            pw.println("PROGRESO;" + partida.getCapituloActual());

            pw.close();
            System.out.println("Partida guardada en " + rutaSave(partida.getUsuario()));
        } catch (IOException e) {
            System.out.println("¡Error al guardar la partida!");
            e.printStackTrace();
        }
    }

    public LogicaJuego cargar(Usuario u) {
        String nombre = "";
        int vida = 100;
        ClaseJugador clase = null;
        Item arma = null;
        Mochila mochila = new Mochila();
        int capitulo = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaSave(u)));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";", -1);
                if (partes[0].equals("JUGADOR")) {
                    nombre = partes[1];
                    vida = Integer.parseInt(partes[2]);
                } else if (partes[0].equals("CLASE")) {
                    clase = buscarClase(partes[1]);
                } else if (partes[0].equals("ARMA")) {
                    arma = buscarItem(partes[1]);
                } else if (partes[0].equals("MOCHILA")) {
                    if (partes.length > 1 && !partes[1].isEmpty()) {
                        String[] nombresItems = partes[1].split(",");
                        for (String n : nombresItems) {
                            Item item = buscarItem(n);
                            if (item != null) mochila.agregarItem(item);
                        }
                    }
                } else if (partes[0].equals("PROGRESO")) {
                    capitulo = Integer.parseInt(partes[1]);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("¡Error al cargar la partida!");
            e.printStackTrace();
            return null;
        }

        Jugador jugador = new Jugador(u, clase, nombre, vida, arma, mochila);
        return new LogicaJuego(u, jugador, capitulo);
    }

    public void borrar(Usuario u) {
        File f = new File(rutaSave(u));
        if (f.exists()) f.delete();
    }

    private Item buscarItem(String nombre) {
        for (int i = 0; i < catalogoItems.length; i++) {
            if (catalogoItems[i] != null && catalogoItems[i].getNombre().equals(nombre)) {
                return catalogoItems[i];
            }
        }
        return null;
    }

    private ClaseJugador buscarClase(String nombre) {
        for (int i = 0; i < catalogoClases.length; i++) {
            if (catalogoClases[i] != null && catalogoClases[i].getNombre().equals(nombre)) {
                return catalogoClases[i];
            }
        }
        return null;
    }
}