package edu.ar.uade.modelo;

import java.util.List;

public class TrofeoConstancia extends Trofeo {
    public TrofeoConstancia() {
        super("Constancia", "Trofeo por cumplir a la perfección las rutinas");
    }

    @Override
    public boolean cumpleCondiciones(Socio contexto) {
        //REVIEW: evaluar condiciones
    	List<EjercicioConcretoRealizado> ejerciciosRealizados = contexto.getRutinaDiaria().getEjerciciosRealizados();
    	for (EjercicioConcretoRealizado e : ejerciciosRealizados) {
    		if (e.getSeriesLogradas() < e.getEjercicio().getCantidadSeries()
    				|| e.getRepeticionesLogradas() < e.getEjercicio().getRepeticiones()
    				|| e.getPesoLevantado() < e.getEjercicio().getPesoAsignado())
    			return false;
    	}
    	List<Ejercicio> ejercicios = contexto.getRutinaDiaria().getEjercicios();
    	if (ejercicios.size() == ejerciciosRealizados.size())
    		return true;
    	return false;
    }
}
