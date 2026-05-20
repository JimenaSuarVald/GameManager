package Vista;

import Modelo.Item;
import Modelo.Mochila;
import Modelo.RutaArchivos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class VistaMochila extends JDialog {
    private JList<Item> listaItems;
    private DefaultListModel<Item> modeloLista;
    private JTextArea descripcionItem;
    private JButton botonUsar;
    private JButton botonCerrar;
    private Mochila mochila;
    private boolean modoCombate;

    public VistaMochila(JFrame padre, Mochila mochila, boolean modoCombate) {
        super(padre, "Mochila", true);
        this.mochila = mochila;
        this.modoCombate = modoCombate;

        this.setSize(450, 450);
        this.setLocationRelativeTo(padre);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        modeloLista = new DefaultListModel<>();
        actualizarLista();
        listaItems = new JList<>(modeloLista);
        listaItems.setCellRenderer(new ItemRenderer());
        listaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaItems.addListSelectionListener(e -> mostrarDescripcionSeleccionada());
        JScrollPane scrollLista = new JScrollPane(listaItems);

        descripcionItem = new JTextArea(4, 30);
        descripcionItem.setEditable(false);
        descripcionItem.setLineWrap(true);
        descripcionItem.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(descripcionItem);

        botonUsar = new JButton("Usar");
        botonUsar.setActionCommand("Mochila:Usar");
        botonCerrar = new JButton("Cerrar");
        botonCerrar.setActionCommand("Mochila:Cerrar");

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 5, 5));
        panelBotones.add(botonUsar);
        panelBotones.add(botonCerrar);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.add(scrollLista);
        panelCentral.add(scrollDescripcion);

        this.setLayout(new BorderLayout(5, 5));
        this.add(panelCentral, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);
    }

    public void setControlador(ActionListener c) {
        botonUsar.addActionListener(c);
        botonCerrar.addActionListener(c);
    }

    public Item getItemSeleccionado() {
        return listaItems.getSelectedValue();
    }

    public boolean isModoCombate() {
        return modoCombate;
    }

    public void actualizarLista() {
        modeloLista.clear();
        int cantidad = mochila.getCantidadItems();
        Item[] equipaje = mochila.getEquipaje();
        for (int i = 0; i < cantidad; i++) {
            modeloLista.addElement(equipaje[i]);
        }
    }

    private void mostrarDescripcionSeleccionada() {
        Item item = getItemSeleccionado();
        if (item == null) {
            descripcionItem.setText("");
            return;
        }
        descripcionItem.setText(
                item.getNombre() + "\n" +
                        "Efecto: " + item.getEfecto() + " (" + item.getMagnitud() + ")\n" +
                        item.getDescripcion()
        );
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private static class ItemRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            if (value instanceof Item) {
                Item item = (Item) value;
                label.setText(item.getNombre());
                String ruta = RutaArchivos.resolver("Juego/Recursos/Items/" + item.getNombre() + ".png");
                if (new File(ruta).exists()) {
                    Image img = new ImageIcon(ruta).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(img));
                } else {
                    label.setIcon(null);
                }
            }
            return label;
        }
    }
}