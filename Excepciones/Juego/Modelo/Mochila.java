package Modelo;

public class Mochila {
    private Item[] equipaje;
    private int cantidadItems;

    public Mochila() {
        this.equipaje = new Item[50];
        this.cantidadItems = 0;
    }

    public void agregarItem(Item nuevoItem) {
        if (this.cantidadItems < 50) {
            this.equipaje[this.cantidadItems] = nuevoItem;
            cantidadItems++;

        } else {
            System.out.println("¡La mochila está llena!");
        }
    }

    public int getCantidadItems() {
        return this.cantidadItems;
    }

    public Item[] getEquipaje() {
        return this.equipaje;
    }

    public void usarItem(Item seleccion) {
        for (int i = 0; i < cantidadItems; i++) {
            if (this.equipaje[i].equals(seleccion)) {
                Item itemExtraido = this.equipaje[i];
                for (int j = i; j < cantidadItems - 1; j++) {
                    this.equipaje[j] = this.equipaje[j + 1];
                }
                this.equipaje[cantidadItems - 1] = null;
                this.cantidadItems--;
                break;   // ← añade esta línea
            }
        }
    }

}