package edu.ar.uade.modelo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;

public class ObjetivoMantener extends Objetivo {

	private int pesoInicial;
	private int margenUnidadesPeso;

    public ObjetivoMantener(String descripcion, int margenUnidadesPeso) {
        super(descripcion);
        this.margenUnidadesPeso = margenUnidadesPeso;
    }
    
    public int getPesoInicial() {
    	return this.pesoInicial;
    }

    @Override
    public void revisarObjetivo(Socio socio) {
    	if(socio.getRutinaDiaria().getEjerciciosRealizados().size()>=socio.getRutinaDiaria().getEjercicios().size()
            && socio.getPesoActual()<=pesoInicial+margenUnidadesPeso
            && socio.getPesoActual()>=pesoInicial-margenUnidadesPeso){
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
        socio.setRutinaDiaria(new Rutina(Collections.unmodifiableList(ejerciciosDisponibles)));
    }

    @Override
    public void verProgreso(Socio socio) {
        System.out.println(String.format("Peso Actual: '%s'", socio.getPesoActual()));
        System.out.println(String.format("Peso Inicial: '%s'", this.getPesoInicial()));
    }

}
