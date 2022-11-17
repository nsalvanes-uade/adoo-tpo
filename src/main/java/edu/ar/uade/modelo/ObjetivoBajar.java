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
    
    public int getPesoIdeal() {
    	return this.pesoIdeal;
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
        socio.setRutinaDiaria(new Rutina(Collections.unmodifiableList(ejerciciosDisponibles)));
    }

    @Override
    public void verProgreso(Socio socio) {
        System.out.println(String.format("Peso Actual: '%s'", socio.getPesoActual()));
        System.out.println(String.format("Peso Ideal: '%s'", this.getPesoIdeal()));
    }

    private void calcularIdeal(float pesoActual, float alturaActual, SexoBiologico sexo) {
    	float pesoPorAlturaIdeal = (alturaActual - 1 ) * 100;
    	if (SexoBiologico.FEMENINO.equals(sexo)) {
    		pesoPorAlturaIdeal *= 0.875f;
    	} else {
    		pesoPorAlturaIdeal -= 2;
    	}
        this.pesoIdeal = Math.round(pesoPorAlturaIdeal);
    }

}
