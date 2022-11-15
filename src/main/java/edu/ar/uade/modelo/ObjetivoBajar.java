package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.SexoBiologico;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ObjetivoBajar extends Objetivo {

    private int pesoIdeal;

    public ObjetivoBajar(String descripcion) {
        super(descripcion);
    }

    @Override
    public void revisarObjetivo(Socio socio) {
        if(socio.getPesoActual()<=pesoIdeal){
            this.marcarCumplido();
            this.getNotificador().enviarNotificacion(
                socio,
                "Felicitaciones! Objetivo bajar de peso cumplido. Sugerimos cambiar objetivo a 'Mantener Figura'"
            );
        }
    }

    @Override
    public void generarRutina(Socio socio) {
        calcularIdeal(socio.getPesoActual(), socio.getAltura(), socio.getSexo());
        List<EjercicioConcreto> ejerciciosDisponibles =
            CatalogoEjercicios.getInstancia().getEjerciciosDisponibles()
                .stream()
                .filter(e -> e.getNivelAerobico()>=3)
                .map(EjercicioConcreto::new)
                .collect(Collectors.toList());
        //TODO: Como limitar el tiempo de cada día de entrenamiento a entre 1 y 1 hora y media?
        socio.setRutinaDiaria(new Rutina(Collections.unmodifiableList(ejerciciosDisponibles)));
    }

    private void calcularIdeal(float pesoActual, float alturaActual, SexoBiologico sexo) {
        //REVIEW: Como resolver? Debería hacerlo un sistema externo igual que en ObjetivoTonificar?
    	/* Le metí una ecuación en base a la primera imagen que encontré en google sobre peso ideal por altura y lo diferencié por sexo biológico
    	 * para que tenga una diferencia en el cálculo.
    	 * ej: (1,83m - 1m) * 100kg/m = 83kg) para altura 1,83 el peso ideal es 83kg.
    	 * Por la pregunta de si debe hacerlo un sistema externo la respuesta es no porque el enunciado especifica que lo tiene que hacer
    	 * nuestro sistema.
    	 */
    	float pesoPorAlturaIdeal = (alturaActual - 1 ) * 100;
    	if (sexo == SexoBiologico.FEMENINO) {
    		pesoPorAlturaIdeal *= 0.875f;
    	}
    	else if (sexo == SexoBiologico.MASCULINO) {
    		pesoPorAlturaIdeal -= 2;
    	}
    	if (pesoActual >= pesoPorAlturaIdeal + 3) {
    		this.pesoIdeal = (int)pesoActual-3;
    	}
        this.pesoIdeal = (int)pesoPorAlturaIdeal;
    }

}
