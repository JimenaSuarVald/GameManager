package Control;

import Vista.VistaMochila;
import Modelo.Item;
import Modelo.Jugador;
import Modelo.Combate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMochila implements ActionListener {
    private VistaMochila vista;
    private Jugador jugador;
    private Combate combate;

    public ControladorMochila(VistaMochila vista, Jugador jugador, Combate combate) {
        this.vista = vista;
        this.jugador = jugador;
        this.combate = combate;
        this.vista.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Mochila:Usar")) {
            Item item = vista.getItemSeleccionado();
            if (item == null) {
                vista.mostrarMensaje("Selecciona un objeto primero.");
                return;
            }
            if (combate != null) {
                combate.procesarObjetos(item);
                jugador.getMochila().usarItem(item);
            } else {
                usarFueraDeCombate(item);
            }
            vista.dispose();
        } else if (comando.equals("Mochila:Cerrar")) {
            vista.dispose();
        }
    }

    private void usarFueraDeCombate(Item item) {
        String efecto = item.getEfecto();
        if (efecto.equals("Cura")) {
            jugador.setVida(jugador.getVida() + item.getMagnitud());
            jugador.getMochila().usarItem(item);
            vista.mostrarMensaje("Te has curado " + item.getMagnitud() + " puntos.");
        } else if (efecto.equals("EquipajeAtaque")) {
            jugador.setArma(item);
            jugador.getMochila().usarItem(item);
            vista.mostrarMensaje("Te has equipado: " + item.getNombre());
        } else {
            vista.mostrarMensaje("Este objeto no se puede usar fuera de combate.");
        }
    }
}