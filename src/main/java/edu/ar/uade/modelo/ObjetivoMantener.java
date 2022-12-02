package edu.ar.uade.modelo;

import java.util.Arrays;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;

public class ObjetivoMantener extends Objetivo {

	private int pesoInicial;
	private int margenUnidadesPeso;

    public ObjetivoMantener(int margenUnidadesPeso) {
        super("Mantener la figura");
        this.margenUnidadesPeso = margenUnidadesPeso;
    }

    @Override
    public Rutina internalGenerarRutina(Socio socio) {
        this.pesoInicial = (int) socio.getPesoActual();

        Rutina rutina = new Rutina();
        rutina.calcularEntrenamiento(
            socio.getDiasDeEntrenamiento().size(),
            new RangoNumerico(2, 4),
            Arrays.asList(ExigenciaMuscular.BAJO, ExigenciaMuscular.MEDIO),
            new RangoNumerico(45, 80)
        );
        return rutina;
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
