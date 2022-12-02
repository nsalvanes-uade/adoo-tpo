package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;

import java.util.ArrayList;
import java.util.List;

public class Rutina {

    private static final int DURACION_SEMANAS = 4;

    private List<DiaDeEntrenamiento> entrenamientos;

    private int cantidadDiasEntrenados;
    private DiaDeEntrenamiento ultimoEntrenamientoComenzado;

    private List<IObserverRutina> observadores;

    public Rutina() {
        this.cantidadDiasEntrenados = 0;
        this.entrenamientos = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    public void calcularEntrenamiento(int cantidadDias,
                                      int nivelAerobicoMinimo,
                                      RangoNumerico duracionEntrenamiento) {
        for(int i = 0; i<cantidadDias*DURACION_SEMANAS ; i++) {
            DiaDeEntrenamiento diaDeEntrenamiento = new DiaDeEntrenamiento();
            diaDeEntrenamiento.generarEjercicios(nivelAerobicoMinimo, duracionEntrenamiento);
            entrenamientos.add(diaDeEntrenamiento);
        }
    }

    public void calcularEntrenamiento(int cantidadDias,
                                      int nivelAerobicoMaximo,
                                      List<ExigenciaMuscular> exigenciasPermitidas,
                                      RangoNumerico duracionEntrenamiento) {
        for(int i = 0; i<cantidadDias*DURACION_SEMANAS ; i++) {
            DiaDeEntrenamiento diaDeEntrenamiento = new DiaDeEntrenamiento();
            diaDeEntrenamiento.generarEjercicios(nivelAerobicoMaximo, exigenciasPermitidas, duracionEntrenamiento);
            entrenamientos.add(diaDeEntrenamiento);
        }
    }

    public void calcularEntrenamiento(int cantidadDias,
                                      RangoNumerico rangoNivelAerobicoPermitido,
                                      List<ExigenciaMuscular> exigenciasPermitidas,
                                      RangoNumerico duracionEntrenamiento) {
        for(int i = 0; i<cantidadDias*DURACION_SEMANAS ; i++) {
            DiaDeEntrenamiento diaDeEntrenamiento = new DiaDeEntrenamiento();
            diaDeEntrenamiento.generarEjercicios(rangoNivelAerobicoPermitido, exigenciasPermitidas, duracionEntrenamiento);
            entrenamientos.add(diaDeEntrenamiento);
        }
    }

    public DiaDeEntrenamiento comenzarEntrenamientoDiario() {
        this.ultimoEntrenamientoComenzado = this.getEntrenamientos().get(cantidadDiasEntrenados);
        this.cantidadDiasEntrenados++;
        ultimoEntrenamientoComenzado.iniciar();
        System.out.println(ultimoEntrenamientoComenzado);
        return ultimoEntrenamientoComenzado;
    }

    public void finalizarEntrenamientoDiario() {
        ultimoEntrenamientoComenzado.finalizar();
        this.observadores.forEach(o -> o.notificarEntrenamientoFinalizado(this));
    }

    public void reforzar(){
        this.getEntrenamientos()
                .subList(cantidadDiasEntrenados, this.getEntrenamientos().size())
                .forEach(e -> e.getEjercicios().forEach(Ejercicio::reforzar));
    }

    public boolean isFinalizada() {
        return cantidadDiasEntrenados >= entrenamientos.size()
            && ultimoEntrenamientoComenzado.isFinalizado();
    }

    public boolean isCumplida() {
        return isFinalizada()
            && entrenamientos.stream().filter(e -> !e.isCumplido()).findAny().isEmpty();
    }
    public void agregarObservador(IObserverRutina observador) {
        this.observadores.add(observador);
    }

    public void eliminarObservador(IObserverRutina observador) {
        this.observadores.remove(observador);
    }

    public List<DiaDeEntrenamiento> getEntrenamientos() {
        return entrenamientos;
    }

    public int getCantidadDiasEntrenados() {
        return cantidadDiasEntrenados;
    }

    public DiaDeEntrenamiento getUltimoEntrenamientoComenzado() {
        return this.ultimoEntrenamientoComenzado;
    }

}
