package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;
import edu.ar.uade.servicios.ICalculadorIdealExternoAdapter;

import java.util.Arrays;

public class ObjetivoTonificar extends Objetivo {

    private float masaMuscularIdeal;
    private float grasaCorporalIdeal;

    private ICalculadorIdealExternoAdapter servicioCalculador;

    public ObjetivoTonificar(ICalculadorIdealExternoAdapter servicioCalculador) {
        super("Tonificar cuerpo");
        this.servicioCalculador = servicioCalculador;
    }

    @Override
    public Rutina internalGenerarRutina(Socio socio) {
        calcularIdeal(socio);

        Rutina rutina = new Rutina();
        rutina.calcularEntrenamiento(
            socio.getDiasDeEntrenamiento().size(),
            4,
            Arrays.asList(ExigenciaMuscular.ALTO),
            new RangoNumerico(120, 150)
        );
        return rutina;
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

    private void calcularIdeal(Socio socio) {
        this.masaMuscularIdeal = servicioCalculador.calcularMasaMuscular(socio.getPesoActual(), socio.getAltura(), socio.getSexo());
        this.grasaCorporalIdeal = servicioCalculador.calcularGrasaCorporal(socio.getPesoActual(), socio.getAltura(), socio.getSexo());
    }

    public float getMasaMuscularIdeal() {
        return this.masaMuscularIdeal;
    }

    public float getGrasaCorporalIdeal() {
        return this.grasaCorporalIdeal;
    }

}
