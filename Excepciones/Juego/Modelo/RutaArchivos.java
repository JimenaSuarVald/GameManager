package Modelo;

import java.io.File;

public class RutaArchivos {
    private static String raiz = encontrarRaiz();

    private static String encontrarRaiz() {
        File start = new File(System.getProperty("user.dir"));

        // 1. Hacia arriba
        File encontrada = buscarHaciaArriba(start);
        if (encontrada != null) return encontrada.getAbsolutePath();

        // 2. Hacia abajo (3 niveles)
        encontrada = buscarHaciaAbajo(start, 3);
        if (encontrada != null) return encontrada.getAbsolutePath();

        // 3. Desde la ubicacion del .class
        try {
            File classDir = new File(RutaArchivos.class
                    .getProtectionDomain().getCodeSource().getLocation().toURI());
            encontrada = buscarHaciaArriba(classDir);
            if (encontrada != null) return encontrada.getAbsolutePath();
            encontrada = buscarHaciaAbajo(classDir, 5);
            if (encontrada != null) return encontrada.getAbsolutePath();
        } catch (Exception e) {
            // ignorar
        }

        System.out.println("ADVERTENCIA: No se encontro la carpeta 'Juego'. Usando: " + start.getAbsolutePath());
        return start.getAbsolutePath();
    }

    private static File buscarHaciaArriba(File desde) {
        File actual = desde;
        while (actual != null) {
            if (new File(actual, "Juego").isDirectory()) {
                return actual;
            }
            actual = actual.getParentFile();
        }
        return null;
    }

    private static File buscarHaciaAbajo(File desde, int profundidadMax) {
        if (desde == null || !desde.isDirectory() || profundidadMax < 0) return null;
        if (new File(desde, "Juego").isDirectory()) return desde;
        File[] hijos = desde.listFiles();
        if (hijos == null) return null;
        for (int i = 0; i < hijos.length; i++) {
            if (hijos[i].isDirectory() && !hijos[i].getName().startsWith(".") && !hijos[i].getName().equals("out")) {
                File r = buscarHaciaAbajo(hijos[i], profundidadMax - 1);
                if (r != null) return r;
            }
        }
        return null;
    }

    public static String resolver(String rutaRelativa) {
        return raiz + File.separator + rutaRelativa.replace('/', File.separatorChar);
    }
}