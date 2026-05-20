package Modelo;

import java.io.File;

public class RutaArchivos {
    private static String raiz = encontrarRaiz();

    private static String encontrarRaiz() {
        File dir = new File(System.getProperty("user.dir"));
        while (dir != null) {
            if (new File(dir, "Juego").isDirectory()) {
                return dir.getAbsolutePath();
            }
            dir = dir.getParentFile();
        }
        return System.getProperty("user.dir");
    }

    public static String resolver(String rutaRelativa) {
        return raiz + File.separator + rutaRelativa.replace('/', File.separatorChar);
    }
}