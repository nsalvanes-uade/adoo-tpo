package edu.ar.uade.modelo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;

public class ObjetivoMantener extends Objetivo {

	private int pesoInicial;
	private int margenUnidadesPeso;
	
    //REVIEW: Pendiente
    public ObjetivoMantener(String descripcion) {
        super(descripcion);
    }

    @Override
    public void revisarObjetivo(Socio socio) {
    	if(socio.getPesoActual()<=pesoInicial+margenUnidadesPeso && socio.getPesoActual()>=pesoInicial-margenUnidadesPeso){
            this.marcarCumplido();
            this.getNotificador().enviarNotificacion(
                socio,
                "Felicitaciones! Objetivo mantener figura cumplido.'"
            );
        }
    }

    @Override
    public void generarRutina(Socio socio) {
    	this.pesoInicial = (int) socio.getPesoActual();
    	List<EjercicioConcreto> ejerciciosDisponibles =
                CatalogoEjercicios.getInstancia().getEjerciciosDisponibles()
                    .stream()
                    .filter(e -> e.getNivelAerobico()<=4 && e.getNivelAerobico()>=2)
                    .filter(e -> ExigenciaMuscular.BAJO.equals(e.getNivelMuscular()) || ExigenciaMuscular.MEDIO.equals(e.getNivelMuscular()))
                        .map(EjercicioConcreto::new)
                    .collect(Collectors.toList());
            //TODO: Como limitar el tiempo de cada día de entrenamiento a entre 45 min y 1 hora y 20?
            socio.setRutinaDiaria(new Rutina(Collections.unmodifiableList(ejerciciosDisponibles)));
    }
}
