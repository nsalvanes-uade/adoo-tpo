package edu.ar.uade.modelo;

import java.util.List;

public class Rutina {

    public static final int DURACION_SEMANAS = 4;

    private List<DiaDeEntrenamiento> entrenamientos;

    private int cantidadDiasEntrenados;
    private DiaDeEntrenamiento ultimoEntrenamientoComenzado;

    public Rutina(List<DiaDeEntrenamiento> entrenamientos) {
        this.entrenamientos = entrenamientos;
        this.cantidadDiasEntrenados = 0;
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
