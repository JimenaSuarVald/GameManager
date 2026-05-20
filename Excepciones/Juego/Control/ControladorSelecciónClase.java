package Control;

import Vista.SelecciónDeClase;
import Vista.MenuPrincipal;
import Modelo.ClaseJugador;
import Modelo.Jugador;
import Modelo.Mochila;
import Modelo.Item;
import Modelo.Usuario;
import Modelo.GestorArchivos;
import Modelo.LogicaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSelecciónClase implements ActionListener {
    private SelecciónDeClase vista;
    private MenuPrincipal menuPrincipal;
    private Usuario usuario;
    private String nombreJugador;
    private ClaseJugador[] clases;
    private Item[] catalogoItems;

    public ControladorSelecciónClase(SelecciónDeClase vista, MenuPrincipal menuPrincipal,
                                     Usuario usuario, String nombreJugador,
                                     ClaseJugador[] clases) {
        this.vista = vista;
        this.menuPrincipal = menuPrincipal;
        this.usuario = usuario;
        this.nombreJugador = nombreJugador;
        this.clases = clases;

        GestorArchivos gestor = new GestorArchivos();
        this.catalogoItems = gestor.cargarItems("Juego/Listas/Objetos");

        this.vista.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.startsWith("CLASE:")) {
            int indice = Integer.parseInt(comando.substring(6));
            ClaseJugador clase = clases[indice];

            Item arma = crearArmaInicial(clase);
            Mochila mochila = crearMochilaInicial();

            Jugador jugador = new Jugador(usuario, clase, nombreJugador, 100, arma, mochila);

            LogicaJuego partida = new LogicaJuego(usuario, jugador, 0);
            vista.dispose();
            new ControladorJuego(partida, menuPrincipal);
        }
    }

    private Item crearArmaInicial(ClaseJugador clase) {
        String nombre = clase.getNombre();
        if (nombre.equals("Estudiante")) {
            return catalogoItems[7];   // Cuaderno viejo
        } else if (nombre.equals("Profesor")) {
            return catalogoItems[10];  // Tiza desgastada
        } else if (nombre.equals("Consultor")) {
            return catalogoItems[13];  // Teclado desgastado desgastado
        } else {
            return catalogoItems[7];   // por defecto, Cuaderno viejo
        }
    }

    private Mochila crearMochilaInicial() {
        Mochila m = new Mochila();
        m.agregarItem(catalogoItems[0]);   // Vial Pequeño
        m.agregarItem(catalogoItems[0]);
        return m;
    }
}