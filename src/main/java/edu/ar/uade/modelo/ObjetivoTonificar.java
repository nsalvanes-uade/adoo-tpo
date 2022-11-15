package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;
import edu.ar.uade.modelo.enumeradores.SexoBiologico;
import edu.ar.uade.servicios.ICalculadorIdealExternoAdapter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ObjetivoTonificar extends Objetivo {

    private float masaMuscularIdeal;
    private float grasaCorporalIdeal;

    private ICalculadorIdealExternoAdapter servicioCalculador;

    public ObjetivoTonificar(String descripcion, ICalculadorIdealExternoAdapter servicioCalculador) {
        super(descripcion);
        this.servicioCalculador = servicioCalculador;
    }
    
    public float getMasaMuscularIdeal() {
    	return this.masaMuscularIdeal;
    }
    
    public float getGrasaCorporalIdeal() {
    	return this.grasaCorporalIdeal;
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

    @Override
    public void generarRutina(Socio socio) {
        calcularIdeal(socio.getPesoActual(), socio.getAltura(), socio.getSexo());
        List<EjercicioConcreto> ejerciciosDisponibles =
            CatalogoEjercicios.getInstancia().getEjerciciosDisponibles()
                .stream()
                .filter(e -> e.getNivelAerobico()<=4)
                .filter(e -> ExigenciaMuscular.ALTO.equals(e.getNivelMuscular()))
                    .map(EjercicioConcreto::new)
                .collect(Collectors.toList());
        //TODO: Como limitar el tiempo de cada día de entrenamiento a entre 2 y 2 horas y media?
        socio.setRutinaDiaria(new Rutina(Collections.unmodifiableList(ejerciciosDisponibles)));
    }

    private void calcularIdeal(float pesoActual, float alturaActual, SexoBiologico sexo) {
        this.masaMuscularIdeal = servicioCalculador.calcularMasaMuscular(pesoActual, alturaActual, sexo);
        this.grasaCorporalIdeal = servicioCalculador.calcularGrasaCorporal(pesoActual, alturaActual, sexo);
    }

}
