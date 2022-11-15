package edu.ar.uade.modelo;

public abstract class Ejercicio {

    private String nombre;

    public Ejercicio(String nombre) {
        this.nombre = nombre;
    }

    public abstract void mostrar();
    public abstract void reforzar();

    public String getNombre() {
        return nombre;
    }
}
