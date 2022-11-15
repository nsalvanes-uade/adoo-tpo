package edu.ar.uade.modelo;

import java.util.ArrayList;
import java.util.List;

public class EjercicioCombinado extends Ejercicio {

    private List<Ejercicio> ejercicios;

    public EjercicioCombinado(String nombre) {
        super(nombre);
        this.ejercicios = new ArrayList<>();
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.add(ejercicio);
    }

    public void eliminarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.remove(ejercicio);
    }

    @Override
    public void mostrar() {
        this.ejercicios.forEach(Ejercicio::mostrar);
    }

    @Override
    public void reforzar() {
        this.ejercicios.forEach(Ejercicio::reforzar);
    }

}
