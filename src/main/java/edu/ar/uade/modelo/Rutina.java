package edu.ar.uade.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Rutina {

    private List<Ejercicio> ejercicios;
    private LocalDate fechaFin;

    //Se utiliza una lista para facilitar implementación
    private List<EjercicioConcretoRealizado> ejerciciosRealizados;

    public Rutina(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
        this.fechaFin = LocalDateTime.now().plusWeeks(4).toLocalDate();
        this.ejerciciosRealizados = new ArrayList<>();
    }

    public void mostrarEjercicios() {
        this.ejercicios.forEach(Ejercicio::mostrar);
    }

    public void reforzar(){
        this.ejercicios.forEach(Ejercicio::reforzar);
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public List<EjercicioConcretoRealizado> getEjerciciosRealizados() {
        return ejerciciosRealizados;
    }
}
