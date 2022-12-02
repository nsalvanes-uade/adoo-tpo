package edu.ar.uade.modelo;

import java.util.*;

public abstract class Objetivo {

    private String descripcion;

    private boolean cumplido;
    private Rutina rutina;
    private Notificador notificador;

    private List<IObserverObjetivo> observadores;

    public Objetivo(String descripcion) {
        this.descripcion = descripcion;
        this.cumplido = false;
        this.notificador = new Notificador();
        this.observadores = new ArrayList<>();
    }

    public void generarRutina(Socio socio) {
        this.rutina = internalGenerarRutina(socio);
        this.rutina.agregarObservador(new ObservadorTrofeoConstancia(socio));
    }

    public abstract Rutina internalGenerarRutina(Socio socio);

    public abstract void revisarObjetivo(Socio socio);
    public abstract void verProgreso(Socio socio);

    public void agregarObservador(IObserverObjetivo observador) {
        this.observadores.add(observador);
    }

    public void eliminarObservador(IObserverObjetivo observador) {
        this.observadores.remove(observador);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCumplido() {
        return cumplido;
    }

    protected void marcarCumplido() {
        this.cumplido = true;
        this.observadores.forEach(o -> o.notificarObjetivoCumplido(this));
    }

    public Rutina getRutina() {
        return rutina;
    }

    public Notificador getNotificador() {
        return notificador;
    }
}
