package edu.ar.uade.modelo;

import java.time.LocalDate;

public class Medicion {

    private float peso;
    private float masaMuscular;
    private float grasaCorporal;
    private LocalDate fecha;

    public Medicion(float peso, float masaMuscular, float grasaCorporal) {
        this.peso = peso;
        this.masaMuscular = masaMuscular;
        this.grasaCorporal = grasaCorporal;
        this.fecha = LocalDate.now();
    }

    public float getPeso() {
        return peso;
    }

    public float getMasaMuscular() {
        return masaMuscular;
    }

    public float getGrasaCorporal() {
        return grasaCorporal;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
