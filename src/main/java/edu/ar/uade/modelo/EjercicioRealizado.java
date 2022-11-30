package edu.ar.uade.modelo;

import java.time.LocalDate;

public class EjercicioRealizado {

    private Ejercicio ejercicio;
    private int seriesLogradas;
    private int repeticionesLogradas;
    private float pesoLevantado;
    private LocalDate fecha;

    public EjercicioRealizado(Ejercicio ejercicio, int seriesLogradas, int repeticionesLogradas, float pesoLevantado) {
        this.ejercicio = ejercicio;
        this.seriesLogradas = seriesLogradas;
        this.repeticionesLogradas = repeticionesLogradas;
        this.pesoLevantado = pesoLevantado;
        this.fecha = LocalDate.now();
    }

    public boolean isCumplido() {
        return getSeriesLogradas() >= getEjercicio().getCantidadSeries()
            && getRepeticionesLogradas() >= getEjercicio().getRepeticiones()
            && getPesoLevantado() >= getEjercicio().getPesoAsignado();
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public int getSeriesLogradas() {
        return seriesLogradas;
    }

    public int getRepeticionesLogradas() {
        return repeticionesLogradas;
    }

    public float getPesoLevantado() {
        return pesoLevantado;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
