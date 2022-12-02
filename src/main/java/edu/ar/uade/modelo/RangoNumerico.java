package edu.ar.uade.modelo;

public class RangoNumerico {

    private int min;
    private int max;

    public RangoNumerico(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean incluye(int valor) {
        return this.min <= valor && valor <= this.max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
