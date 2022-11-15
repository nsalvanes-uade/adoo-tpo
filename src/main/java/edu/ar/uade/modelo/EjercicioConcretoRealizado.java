package edu.ar.uade.modelo;

public class EjercicioConcretoRealizado {

    private EjercicioConcreto ejercicio;
    private int seriesLogradas;
    private int repeticionesLogradas;
    private float pesoLevantado;

    public EjercicioConcretoRealizado(EjercicioConcreto ejercicio, int seriesLogradas, int repeticionesLogradas, float pesoLevantado) {
        this.ejercicio = ejercicio;
        this.seriesLogradas = seriesLogradas;
        this.repeticionesLogradas = repeticionesLogradas;
        this.pesoLevantado = pesoLevantado;
    }

    public EjercicioConcreto getEjercicio() {
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
}
