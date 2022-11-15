package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;
import edu.ar.uade.modelo.enumeradores.GrupoMuscular;

public class EjercicioConcreto extends Ejercicio {

    private GrupoMuscular grupo;
    private int cantidadSeries;
    private int repeticiones;
    private float pesoAsignado;
    private int nivelAerobico;
    private ExigenciaMuscular nivelMuscular;
    private EjercicioVideo video;

    private final float INDICE_REFUERZO = 1.25f;

    public EjercicioConcreto(String nombre, GrupoMuscular grupo, int cantidadSeries, int repeticiones, float pesoAsignado,
                             int nivelAerobico, ExigenciaMuscular nivelMuscular, EjercicioVideo video) {
        super(nombre);
        this.grupo = grupo;
        this.cantidadSeries = cantidadSeries;
        this.repeticiones = repeticiones;
        this.pesoAsignado = pesoAsignado;
        this.nivelAerobico = nivelAerobico;
        this.nivelMuscular = nivelMuscular;
        this.video = video;
    }

    public EjercicioConcreto(EjercicioConcreto ejercicioACopiar) {
        super(ejercicioACopiar.getNombre());
        this.grupo = ejercicioACopiar.getGrupo();
        this.cantidadSeries = ejercicioACopiar.getCantidadSeries();
        this.repeticiones = ejercicioACopiar.getRepeticiones();
        this.pesoAsignado = ejercicioACopiar.getPesoAsignado();
        this.nivelAerobico = ejercicioACopiar.getNivelAerobico();
        this.nivelMuscular = ejercicioACopiar.getNivelMuscular();
        this.video = new EjercicioVideo(ejercicioACopiar.getVideo().getRutaArchivo());
    }

    @Override
    public void mostrar() {
        //TODO: Implementar
    }

    @Override
    public void reforzar() {
        cantidadSeries = Math.round(cantidadSeries * INDICE_REFUERZO);
        repeticiones = Math.round(repeticiones * INDICE_REFUERZO);
        pesoAsignado = Math.round(pesoAsignado * INDICE_REFUERZO);
    }

    public GrupoMuscular getGrupo() {
        return grupo;
    }

    public int getCantidadSeries() {
        return cantidadSeries;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public float getPesoAsignado() {
        return pesoAsignado;
    }

    public int getNivelAerobico() {
        return nivelAerobico;
    }

    public ExigenciaMuscular getNivelMuscular() {
        return nivelMuscular;
    }

    public EjercicioVideo getVideo() {
        return video;
    }

    public void setCantidadSeries(int cantidadSeries) {
        this.cantidadSeries = cantidadSeries;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public void setPesoAsignado(float pesoAsignado) {
        this.pesoAsignado = pesoAsignado;
    }
}
