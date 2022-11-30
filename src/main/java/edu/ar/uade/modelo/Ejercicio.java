package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;
import edu.ar.uade.modelo.enumeradores.GrupoMuscular;

public class Ejercicio {

    private String nombre;
    private GrupoMuscular grupo;
    private int cantidadSeries;
    private int repeticiones;
    private float pesoAsignado;
    private int nivelAerobico;
    private ExigenciaMuscular nivelMuscular;
    private int minDuracion;
    private EjercicioVideo video;

    private final float INDICE_REFUERZO = 1.25f;

    public Ejercicio(String nombre, GrupoMuscular grupo, int cantidadSeries, int repeticiones, float pesoAsignado,
                     int nivelAerobico, ExigenciaMuscular nivelMuscular, int minDuracion, EjercicioVideo video) {
        this.nombre = nombre;
        this.grupo = grupo;
        this.cantidadSeries = cantidadSeries;
        this.repeticiones = repeticiones;
        this.pesoAsignado = pesoAsignado;
        this.nivelAerobico = nivelAerobico;
        this.nivelMuscular = nivelMuscular;
        this.minDuracion = minDuracion;
        this.video = video;
    }

    public Ejercicio(Ejercicio ejercicioACopiar) {
        this.nombre = ejercicioACopiar.getNombre();
        this.grupo = ejercicioACopiar.getGrupo();
        this.cantidadSeries = ejercicioACopiar.getCantidadSeries();
        this.repeticiones = ejercicioACopiar.getRepeticiones();
        this.pesoAsignado = ejercicioACopiar.getPesoAsignado();
        this.nivelAerobico = ejercicioACopiar.getNivelAerobico();
        this.nivelMuscular = ejercicioACopiar.getNivelMuscular();
        this.minDuracion = ejercicioACopiar.getMinDuracion();
        this.video = new EjercicioVideo(ejercicioACopiar.getVideo().getRutaArchivo());
    }

    public void reforzar() {
        cantidadSeries = Math.round(cantidadSeries * INDICE_REFUERZO);
        repeticiones = Math.round(repeticiones * INDICE_REFUERZO);
        pesoAsignado = Math.round(pesoAsignado * INDICE_REFUERZO);
    }

    public String getNombre() {
        return nombre;
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

    public int getMinDuracion() {
        return minDuracion;
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

    @Override
    public String toString() {
        return String.format("\nEjercicio %s\n" +
                        "- Valores asignados: %d series de %d repeticiones c/u, con %f kg.\n" +
                        "- Link a video demostrativo: %s",
                this.getNombre(),
                this.getCantidadSeries(), this.getRepeticiones(), this.getPesoAsignado(),
                this.getVideo().getRutaArchivo());
    }

}
