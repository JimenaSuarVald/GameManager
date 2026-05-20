package ControlPP;

import VistaPP.VentanaPasapalabra;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControladorPasapalabra implements ActionListener {

    private VentanaPasapalabra dialogo;

    public ControladorPasapalabra(VentanaPasapalabra dialogo) {
        this.dialogo = dialogo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. Cojo el texto que hay en la caja
        String textoEscrito = dialogo.getCajaTexto().getText();

        // 2. Se lo guardo en la variable interna de la ventana
        dialogo.setRespuestaUsuario(textoEscrito);

        // 3. Cierro y destruyo el diálogo. Esto "descongela" el código principal
        dialogo.dispose();
    }
}