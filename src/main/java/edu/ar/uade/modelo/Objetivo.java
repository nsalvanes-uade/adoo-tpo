package edu.ar.uade.modelo;

public abstract class Objetivo {

    private String descripcion;
    private boolean cumplido;
    private Notificador notificador;

    public Objetivo(String descripcion) {
        this.descripcion = descripcion;
        this.cumplido = false;
        this.notificador = new Notificador();
    }

    public abstract void revisarObjetivo(Socio socio);
    public abstract void generarRutina(Socio socio);

    protected void marcarCumplido() {
        this.cumplido = true;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCumplido() {
        return cumplido;
    }

    public Notificador getNotificador() {
        return notificador;
    }
}
