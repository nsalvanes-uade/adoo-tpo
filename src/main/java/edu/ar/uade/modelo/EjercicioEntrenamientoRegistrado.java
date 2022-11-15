package edu.ar.uade.modelo;

import java.time.LocalDate;

public class EjercicioEntrenamientoRegistrado {

    private EjercicioConcretoRealizado ejercicio;
    private LocalDate fecha;

    public EjercicioEntrenamientoRegistrado(EjercicioConcretoRealizado ejercicio, LocalDate fecha) {
        this.ejercicio = ejercicio;
        this.fecha = fecha;
    }

    public EjercicioConcretoRealizado getEjercicio() {
        return ejercicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

}
