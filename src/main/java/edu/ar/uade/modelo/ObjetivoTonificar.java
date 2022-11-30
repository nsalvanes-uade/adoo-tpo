package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;
import edu.ar.uade.servicios.ICalculadorIdealExternoAdapter;

import java.util.stream.Stream;

public class ObjetivoTonificar extends Objetivo {

    private float masaMuscularIdeal;
    private float grasaCorporalIdeal;

    private ICalculadorIdealExternoAdapter servicioCalculador;

    public ObjetivoTonificar(ICalculadorIdealExternoAdapter servicioCalculador) {
        super("Tonificar cuerpo", 120, 150);
        this.servicioCalculador = servicioCalculador;
    }

    @Override
    protected void calcularIdeal(Socio socio) {
        this.masaMuscularIdeal = servicioCalculador.calcularMasaMuscular(socio.getPesoActual(), socio.getAltura(), socio.getSexo());
        this.grasaCorporalIdeal = servicioCalculador.calcularGrasaCorporal(socio.getPesoActual(), socio.getAltura(), socio.getSexo());
    }

    @Override
    protected Stream<Ejercicio> filtrarEjercicios(Stream<Ejercicio> ejerciciosDisponibles) {
        return ejerciciosDisponibles
            .filter(e -> e.getNivelAerobico()<=4)
            .filter(e -> ExigenciaMuscular.ALTO.equals(e.getNivelMuscular()));
    }

    @Override
    public void verProgreso(Socio socio) {
        System.out.println(
            String.format("Masa Muscular Actual: '%s' Grasa Corporal Actual: '%s' ",
                    socio.getMasaCorporalActual(), socio.getGrasaCorporalActual())
        );
        System.out.println(
            String.format("Masa Muscular Ideal: '%s' Grasa Corporal Ideal: '%s' ",
                    this.getMasaMuscularIdeal(), this.getGrasaCorporalIdeal())
        );
    }

    @Override
    public void revisarObjetivo(Socio socio) {
        if(socio.getMasaCorporalActual()>=masaMuscularIdeal && socio.getGrasaCorporalActual()<=grasaCorporalIdeal){
            this.marcarCumplido();
            this.getNotificador().enviarNotificacion(
                    socio,
                    "Felicitaciones! Objetivo bajar de tonificar cumplido. Sugerimos cambiar objetivo a 'Mantener Figura'"
            );
        }
    }

    public float getMasaMuscularIdeal() {
        return this.masaMuscularIdeal;
    }

    public float getGrasaCorporalIdeal() {
        return this.grasaCorporalIdeal;
    }

}
