package Modelo;

import ModeloGen.Persona;

public class Usuario {
    private Persona persona;
    private String nombre;

    public Usuario(Persona persona, String nombre) {
        this.persona = persona;
        this.nombre = nombre;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}