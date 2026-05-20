package Control;

import Vista.VistaObjectBound;
import Vista.MenuPrincipal;
import Vista.MenuPausa;
import Vista.VistaMochila;
import Vista.VistaOpciones;
import Vista.PantallaVictoria;
import Vista.PantallaGameOver;
import Modelo.Jugador;
import Modelo.Historia;
import Modelo.Capitulo;
import Modelo.Combate;
import Modelo.Combate.EstadoCombate;
import Modelo.Enemigos;
import Modelo.GestorArchivos;
import Modelo.GestorPartidas;
import Modelo.LogicaJuego;
import Modelo.Item;
import Modelo.ClaseJugador;
import Modelo.Mochila;
import Modelo.Habilidades;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorJuego implements ActionListener {
    private VistaObjectBound vista;
    private MenuPrincipal menuPrincipal;
    private MenuPausa menuPausa;
    private VistaOpciones vistaOpciones;
    private LogicaJuego partida;
    private Jugador jugador;
    private Capitulo[] capitulos;
    private Enemigos[] catalogoEnemigos;
    private Habilidades[] catalogoHabilidades;
    private Item[] catalogoItems;
    private GestorPartidas gestorPartidas;
    private int capituloActual;
    private Combate combateActual;
    private JFrame pantallaFinal;

    public ControladorJuego(LogicaJuego partida, MenuPrincipal menuPrincipal) {
        this.partida = partida;
        this.jugador = partida.getJugador();
        this.menuPrincipal = menuPrincipal;

        GestorArchivos gestor = new GestorArchivos();
        this.capitulos = new Historia().cargarHistoria("Juego/Listas/Historia");
        this.catalogoEnemigos = gestor.cargarEnemigos("Juego/Listas/Enemigos");
        this.catalogoHabilidades = gestor.cargarHabilidades("Juego/Listas/Habilidades");
        this.catalogoItems = gestor.cargarItems("Juego/Listas/Objetos");
        ClaseJugador[] catalogoClases = gestor.cargarClases("Juego/Listas/Clases");
        this.gestorPartidas = new GestorPartidas(catalogoItems, catalogoClases);

        this.capituloActual = partida.getCapituloActual();
        this.combateActual = null;
        this.vista = new VistaObjectBound();
        this.vista.setControlador(this);
        this.menuPausa = new MenuPausa();
        this.menuPausa.setControlador(this);
        mostrarCapituloActual();
    }

    private String aplicarVariables(String texto) {
        if (texto == null) return "";
        String resultado = texto;
        if (jugador != null) {
            if (jugador.getNombre() != null) {
                resultado = resultado.replace("{nombre}", jugador.getNombre());
            }
            if (jugador.getClaseJugador() != null && jugador.getClaseJugador().getNombre() != null) {
                String nombreClase = jugador.getClaseJugador().getNombre();
                resultado = resultado.replace("{clase}", nombreClase);
                if (nombreClase.equals("Estudiante")) {
                    resultado = resultado.replace("{descripcionClase}",
                            "Estás en tu primer año de la carrera de ingeniería informatica, en tu mano hay un cuaderno" +
                                    " lleno de garabatos lineas de codigo mal copiadas y dibujos de la profesora de física con bigote " +
                                    "Solo recuerdas que estabas a punto de entregar algo muy importante...");
                } else if (nombreClase.equals("Profesor")) {
                    resultado = resultado.replace("{descripcionClase}",
                            "Este es tu primer año enseñando y te dedicas a asegurar que tus alumnos " +
                                    "sepan la diferencia exacta de una clase y un objeto sin embargo hay aquellos que siguen" +
                                    " sin comprenderlo, tenías que preparar algo muy importante pero no te acuerdas de que, " +
                                    "solo sabes que no te han dado la nota del TFG aún");
                } else if (nombreClase.equals("Consultor")) {
                    resultado = resultado.replace("{descripcionClase}",
                            "Apareciste un día en la empresa con un traje caro y un PowerPoint impecable. " +
                                    "Nadie sabe muy bien qué haces, pero cobras tres veces más que el equipo entero. " +
                                    "Solo recuerdas que la última vez que abriste Chat GPT, todo se volvió borroso...");
                }
            }
        }
        return resultado;
    }

    private void mostrarCapituloActual() {
        if (capituloActual >= capitulos.length || capitulos[capituloActual] == null) {
            terminarHistoria();
            return;
        }
        Capitulo c = capitulos[capituloActual];
        vista.cargarEscenario(c.getEscenario());
        if (c.tieneCombate()) {
            iniciarCombate(c);
        } else {
            vista.modoNarrativa(aplicarVariables(c.getTextoNarrativo()));
        }
    }

    private void iniciarCombate(Capitulo c) {
        String[] nombres = c.getNombresEnemigos();
        Enemigos[] enemigosCombate = new Enemigos[nombres.length];
        for (int i = 0; i < nombres.length; i++) {
            enemigosCombate[i] = buscarEnemigo(nombres[i].trim());
        }
        combateActual = new Combate(enemigosCombate, jugador);
        vista.modoCombate();
        vista.cargarEnemigos(nombres);
        vista.mostrarMensaje(aplicarVariables(c.getTextoNarrativo()));
        vista.mostrarMensaje("Combate iniciado.");
        vista.mostrarMensaje("(Tus habilidades se desbloquean tras 1, 2 y 3 turnos respectivamente.)");
        refrescarVistaCombate();
    }

    private Enemigos buscarEnemigo(String nombre) {
        for (int i = 0; i < catalogoEnemigos.length; i++) {
            if (catalogoEnemigos[i] != null && catalogoEnemigos[i].getNombre().equals(nombre)) {
                Enemigos o = catalogoEnemigos[i];
                return new Enemigos(o.getNombre(), o.getDaño(), o.getVida(), o.getTipoDaño());
            }
        }
        System.out.println("Enemigo no encontrado en el catalogo: " + nombre);
        return null;
    }

    private Habilidades buscarHabilidad(String nombre) {
        for (int i = 0; i < catalogoHabilidades.length; i++) {
            if (catalogoHabilidades[i] != null && catalogoHabilidades[i].getNombre().equals(nombre)) {
                return catalogoHabilidades[i];
            }
        }
        return null;
    }

    private Item buscarItem(String nombre) {
        for (int i = 0; i < catalogoItems.length; i++) {
            if (catalogoItems[i] != null && catalogoItems[i].getNombre().equals(nombre)) {
                return catalogoItems[i];
            }
        }
        return null;
    }

    private void refrescarVistaCombate() {
        vista.actualizarVidaJugador(jugador.getVida());
        Enemigos[] enemigos = combateActual.getEnemigos();
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null) {
                vista.actualizarVidaEnemigo(i, enemigos[i].getNombre(), enemigos[i].getVida());
            } else {
                vista.actualizarVidaEnemigo(i, "Enemigo " + (i + 1), 0);
            }
        }
    }

    private void volcarMensajesCombate() {
        String[] msgs = combateActual.getMensajes();
        for (int i = 0; i < msgs.length; i++) {
            vista.mostrarMensaje(msgs[i]);
        }
        combateActual.limpiarMensajes();
    }

    private void comprobarFinCombate() {
        EstadoCombate estado = combateActual.getEstado();
        if (estado == EstadoCombate.VICTORIA) {
            Capitulo c = capitulos[capituloActual];
            String mensaje = "Has vencido el combate.";
            if (c.tieneRecompensas()) {
                mensaje += "\n\nRecompensas obtenidas:";
                for (String n : c.getNombresRecompensas()) {
                    Item item = buscarItem(n.trim());
                    if (item != null) {
                        jugador.getMochila().agregarItem(item);
                        mensaje += "\n- " + item.getNombre();
                    }
                }
            }
            JOptionPane.showMessageDialog(vista, mensaje);
            combateActual = null;
            capituloActual++;
            mostrarCapituloActual();
        } else if (estado == EstadoCombate.DERROTA) {
            combateActual = null;
            gestorPartidas.borrar(partida.getUsuario());
            vista.dispose();
            PantallaGameOver pg = new PantallaGameOver();
            pg.setControlador(this);
            pantallaFinal = pg;
        }
    }

    private void terminarHistoria() {
        gestorPartidas.borrar(partida.getUsuario());
        vista.dispose();
        PantallaVictoria pv = new PantallaVictoria();
        pv.setControlador(this);
        pantallaFinal = pv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Continuar")) {
            capituloActual++;
            mostrarCapituloActual();
        } else if (comando.equals("Atacar") && combateActual != null) {
            atacarEnCombate();
        } else if (comando.equals("Defender") && combateActual != null) {
            combateActual.defender();
            volcarMensajesCombate();
            refrescarVistaCombate();
            comprobarFinCombate();
        } else if (comando.equals("Habilidad") && combateActual != null) {
            usarHabilidadEnCombate();
        } else if (comando.equals("Item") && combateActual != null) {
            usarItemEnCombate();
        } else if (comando.equals("Menu") || comando.equals("Menú")) {
            menuPausa.setVisible(true);
        } else if (comando.equals("Pausa:Continuar")) {
            menuPausa.setVisible(false);
        } else if (comando.equals("Pausa:Mochila")) {
            VistaMochila vm = new VistaMochila(menuPausa, jugador.getMochila(), false);
            new ControladorMochila(vm, jugador, null);
            vm.setVisible(true);
        } else if (comando.equals("Pausa:Guardar")) {
            if (combateActual != null) {
                JOptionPane.showMessageDialog(menuPausa, "No puedes guardar durante un combate.");
            } else {
                partida.setCapituloActual(capituloActual);
                gestorPartidas.guardar(partida);
                JOptionPane.showMessageDialog(menuPausa, "Partida guardada.");
            }
        } else if (comando.equals("Pausa:Opciones")) {
            vistaOpciones = new VistaOpciones(menuPausa, partida.getUsuario());
            vistaOpciones.setControlador(this);
            vistaOpciones.setVisible(true);
        } else if (comando.equals("Opciones:BorrarPartida")) {
            gestorPartidas.borrar(partida.getUsuario());
            JOptionPane.showMessageDialog(vistaOpciones, "Partida borrada. Se borrará al volver al menú.");
        } else if (comando.equals("Opciones:Cerrar")) {
            if (vistaOpciones != null) vistaOpciones.dispose();
        } else if (comando.equals("Pausa:Menu")) {
            int r = JOptionPane.showConfirmDialog(menuPausa,
                    "Volver al menu principal? Perderas el progreso no guardado.",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                menuPausa.setVisible(false);
                menuPausa.dispose();
                vista.dispose();
                menuPrincipal.setVisible(true);
            }
        } else if (comando.equals("FinJuego:Menu")) {
            if (pantallaFinal != null) {
                pantallaFinal.dispose();
                pantallaFinal = null;
            }
            menuPrincipal.setVisible(true);
        }  else if (comando.equals("FinJuego:Salir")) {
        if (pantallaFinal != null) pantallaFinal.dispose();
        if (menuPausa != null) menuPausa.dispose();
        if (vista != null) vista.dispose();
        if (menuPrincipal != null) menuPrincipal.dispose();
    }
    }

    private void atacarEnCombate() {
        String[] nombresVivos = nombresEnemigosVivos();
        if (nombresVivos.length == 0) return;
        int posicion = vista.seleccionarEnemigoObjetivo(nombresVivos);
        if (posicion == -1) return;
        int indiceReal = indiceEnemigoVivo(posicion);
        combateActual.atacar(indiceReal);
        volcarMensajesCombate();
        refrescarVistaCombate();
        comprobarFinCombate();
    }

    private void usarItemEnCombate() {
        Mochila mochila = jugador.getMochila();
        if (mochila.getCantidadItems() == 0) {
            JOptionPane.showMessageDialog(vista, "Tu mochila esta vacia.");
            return;
        }
        VistaMochila vm = new VistaMochila(vista, mochila, true);
        new ControladorMochila(vm, jugador, combateActual);
        vm.setVisible(true);
        volcarMensajesCombate();
        refrescarVistaCombate();
        comprobarFinCombate();
    }

    private void usarHabilidadEnCombate() {
        String[] nombres = jugador.getClaseJugador().getHabilidades();
        Habilidades[] habilidades = new Habilidades[nombres.length];
        int disponibles = 0;
        for (int i = 0; i < nombres.length; i++) {
            if (combateActual.puedeUsarHabilidad(i)) {
                habilidades[i] = buscarHabilidad(nombres[i]);
                if (habilidades[i] != null) disponibles++;
            }
        }
        if (disponibles == 0) {
            JOptionPane.showMessageDialog(vista,
                    "Aún no tienes habilidades disponibles. La primera se desbloquea tras el primer turno.");
            return;
        }
        String seleccion = vista.seleccionarHabilidad(habilidades);
        if (seleccion == null) return;
        Habilidades habilidad = buscarHabilidad(seleccion);
        if (habilidad == null) {
            vista.mostrarMensaje("Habilidad no encontrada: " + seleccion);
            return;
        }
        combateActual.usarHabilidad(habilidad);
        volcarMensajesCombate();
        refrescarVistaCombate();
        comprobarFinCombate();
    }

    private String[] nombresEnemigosVivos() {
        Enemigos[] enemigos = combateActual.getEnemigos();
        int vivos = 0;
        for (Enemigos en : enemigos) {
            if (en != null && en.getVida() > 0) vivos++;
        }
        String[] nombres = new String[vivos];
        int j = 0;
        for (Enemigos en : enemigos) {
            if (en != null && en.getVida() > 0) {
                nombres[j++] = en.getNombre();
            }
        }
        return nombres;
    }

    private int indiceEnemigoVivo(int posicionEnLista) {
        Enemigos[] enemigos = combateActual.getEnemigos();
        int contador = 0;
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null && enemigos[i].getVida() > 0) {
                if (contador == posicionEnLista) return i;
                contador++;
            }
        }
        return -1;
    }
}