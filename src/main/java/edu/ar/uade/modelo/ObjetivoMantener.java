package edu.ar.uade.modelo;

import java.util.stream.Stream;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;

public class ObjetivoMantener extends Objetivo {

	private int pesoInicial;
	private int margenUnidadesPeso;

    public ObjetivoMantener(int margenUnidadesPeso) {
        super("Mantener la figura", 45, 80);
        this.margenUnidadesPeso = margenUnidadesPeso;
    }

    @Override
    protected void calcularIdeal(Socio socio) {
        this.pesoInicial = (int) socio.getPesoActual();
    }

    @Override
    protected Stream<Ejercicio> filtrarEjercicios(Stream<Ejercicio> ejerciciosDisponibles) {
    	return ejerciciosDisponibles
            .filter(e -> e.getNivelAerobico()<=4 && e.getNivelAerobico()>=2)
            .filter(e -> ExigenciaMuscular.BAJO.equals(e.getNivelMuscular()) || ExigenciaMuscular.MEDIO.equals(e.getNivelMuscular()));
    }

    @Override
    public void verProgreso(Socio socio) {
        System.out.println(String.format("Peso Actual: '%s'", socio.getPesoActual()));
        System.out.println(String.format("Peso Inicial: '%s'", this.getPesoInicial()));
    }

    @Override
    public void revisarObjetivo(Socio socio) {
        if(getRutina().getCantidadDiasEntrenados()>=getRutina().getEntrenamientos().size()
                && socio.getPesoActual()<=pesoInicial+margenUnidadesPeso
                && socio.getPesoActual()>=pesoInicial-margenUnidadesPeso){
            this.marcarCumplido();
            this.getNotificador().enviarNotificacion(
                    socio,
                    "Felicitaciones! Objetivo mantener figura cumplido."
            );
        }
    }

    public int getPesoInicial() {
        return this.pesoInicial;
    }

}
