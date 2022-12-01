package edu.ar.uade.modelo;

import java.util.ArrayList;
import java.util.List;

public class Rutina {

    public static final int DURACION_SEMANAS = 4;

    private List<DiaDeEntrenamiento> entrenamientos;

    private int cantidadDiasEntrenados;
    private DiaDeEntrenamiento ultimoEntrenamientoComenzado;

    private List<IObserverRutina> observadores;

    public Rutina(List<DiaDeEntrenamiento> entrenamientos) {
        this.entrenamientos = entrenamientos;
        this.cantidadDiasEntrenados = 0;
        this.observadores = new ArrayList<>();
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
