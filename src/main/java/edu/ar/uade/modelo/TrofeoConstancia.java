package edu.ar.uade.modelo;

import java.util.List;

public class TrofeoConstancia extends Trofeo {
    public TrofeoConstancia() {
        super("Constancia", "Trofeo por cumplir a la perfección las rutinas");
    }

    @Override
    public boolean cumpleCondiciones(Socio contexto) {
        //REVIEW: evaluar condiciones
    	/* Evaluación del problema:
    	 * Esto debería de dispararse al finalizar una rutina.
    	 * Y debería de evaluar si se cumplieron a la perfección cada uno de sus ejercicios.
    	 * Estrategia:
    	 * 1-Obtener los ejercicios realizados correspondientes a la rutina.
    	 * 2-Compararlos con los ejercicios asignados a esa misma rutina.
    	 * 3-Verificar que hizo la misma cantidad de ejercicios que los que se le asignó.
    	 * 4-Profit.
    	 */
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
