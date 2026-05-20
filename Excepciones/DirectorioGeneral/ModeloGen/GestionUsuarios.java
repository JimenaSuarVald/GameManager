package ModeloGen;

import Modelo.RutaArchivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GestionUsuarios {
    private Persona[] usuarios;
    private int cantidad;
    private static final int MAX = 200;

    public GestionUsuarios() {
        usuarios = new Persona[MAX];
        cantidad = 0;
        cargarUsuarios();
        if (cantidad == 0) {
            usuarios[cantidad++] = new Administrador("admin", "admin123");
            usuarios[cantidad++] = new Jugador("jose", "1234");
            guardarUsuarios();
        }
    }

    public boolean registrarJugador(String nombre, String contraseña) {
        if (existeUsuario(nombre) || cantidad >= MAX) return false;
        usuarios[cantidad++] = new Jugador(nombre, contraseña);
        guardarUsuarios();
        return true;
    }

    public Persona iniciarSesion(String nombre, String contraseña) {
        for (int i = 0; i < cantidad; i++) {
            if (usuarios[i].getNombre().equals(nombre)
                    && usuarios[i].getContraseña().equals(contraseña)) {
                usuarios[i].incrementarLogin();
                guardarUsuarios();
                return usuarios[i];
            }
        }
        return null;
    }

    public boolean existeUsuario(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (usuarios[i].getNombre().equals(nombre)) return true;
        }
        return false;
    }

    public Persona[] getUsuarios() {
        Persona[] copia = new Persona[cantidad];
        for (int i = 0; i < cantidad; i++) copia[i] = usuarios[i];
        return copia;
    }

    public void guardarUsuarios() {
        try {
            String ruta = RutaArchivos.resolver("DirectorioGeneral/usuarios.txt");
            File f = new File(ruta);
            if (f.getParentFile() != null) f.getParentFile().mkdirs();
            PrintWriter pw = new PrintWriter(new FileWriter(ruta));
            for (int i = 0; i < cantidad; i++) {
                Persona p = usuarios[i];
                String tipo = (p instanceof Administrador) ? "ADMIN" : "JUGADOR";
                if (p instanceof Jugador) {
                    Jugador j = (Jugador) p;
                    pw.println(tipo + ";" + j.getNombre() + ";" + j.getContraseña()
                            + ";" + j.getNumeroLogins()
                            + ";" + j.getPuntuacionTotal()
                            + ";" + j.getRecord()
                            + ";" + j.getPartidasJugadas()
                            + ";" + j.getVictorias());
                } else {
                    pw.println(tipo + ";" + p.getNombre() + ";" + p.getContraseña()
                            + ";" + p.getNumeroLogins());
                }
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("No se pudo guardar usuarios: " + e.getMessage());
        }
    }

    private void cargarUsuarios() {
        try {
            String ruta = RutaArchivos.resolver("DirectorioGeneral/usuarios.txt");
            File f = new File(ruta);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = br.readLine()) != null && cantidad < MAX) {
                String[] p = linea.split(";", -1);
                if (p.length < 4) continue;
                String tipo = p[0];
                String nombre = p[1];
                String pass = p[2];
                int logins = Integer.parseInt(p[3]);
                if (tipo.equals("ADMIN")) {
                    Administrador a = new Administrador(nombre, pass);
                    a.setNumeroLogins(logins);
                    usuarios[cantidad++] = a;
                } else {
                    Jugador j = new Jugador(nombre, pass);
                    j.setNumeroLogins(logins);
                    if (p.length >= 8) {
                        j.setPuntuacionTotal(Integer.parseInt(p[4]));
                        j.setRecord(Integer.parseInt(p[5]));
                        j.setPartidasJugadas(Integer.parseInt(p[6]));
                        j.setVictorias(Integer.parseInt(p[7]));
                    }
                    usuarios[cantidad++] = j;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("No se pudo cargar usuarios: " + e.getMessage());
        }
    }
}