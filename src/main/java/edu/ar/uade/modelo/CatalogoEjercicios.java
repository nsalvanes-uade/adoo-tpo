package edu.ar.uade.modelo;

import java.util.ArrayList;
import java.util.List;

public class CatalogoEjercicios {

    private static CatalogoEjercicios instancia;

    private List<Ejercicio> ejerciciosDisponibles;

    private CatalogoEjercicios() {
        this.ejerciciosDisponibles = new ArrayList<>();
    }

    public static CatalogoEjercicios getInstancia() {
        if(instancia==null){
            instancia = new CatalogoEjercicios();
        }
        return instancia;
    }

    public List<Ejercicio> getEjerciciosDisponibles() {
        return ejerciciosDisponibles;
    }

    public CatalogoEjercicios agregarEjercicio(Ejercicio nuevoEjercicio) {
        this.getEjerciciosDisponibles().add(nuevoEjercicio);
        return this;
    }

}
