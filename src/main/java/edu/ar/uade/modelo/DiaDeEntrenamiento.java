package edu.ar.uade.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiaDeEntrenamiento {

    private List<Ejercicio> ejercicios;
    //Se utiliza una lista de ejercicios realizados para facilitar implementación
    private List<EjercicioRealizado> ejerciciosRealizados;

    private LocalDate fechaRealizacion;
    private boolean finalizado = false;

    public DiaDeEntrenamiento(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
        this.ejerciciosRealizados = new ArrayList<>();
    }

    public void iniciar() {
        fechaRealizacion = LocalDate.now();
    }

    public void finalizar() {
        finalizado = true;
    }

    public boolean isCumplido() {
        return ejerciciosRealizados.size()==ejercicios.size()
            && ejerciciosRealizados.stream().filter(e -> !e.isCumplido()).findAny().isEmpty();
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public List<EjercicioRealizado> getEjerciciosRealizados() {
        return ejerciciosRealizados;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    @Override
    public String toString() {
        return "DiaDeEntrenamiento{" +
                "ejercicios=" + ejercicios +
                '}';
    }

}
