package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.SexoBiologico;

public class ObjetivoBajar extends Objetivo {

    private int pesoIdeal;

    public ObjetivoBajar() {
        super("Bajar de peso");
    }

    @Override
    public Rutina internalGenerarRutina(Socio socio) {
        calcularIdeal(socio);
        Rutina rutina = new Rutina();
        rutina.calcularEntrenamiento(
            socio.getDiasDeEntrenamiento().size(),
            3,
            new RangoNumerico(60, 90)
        );
        return rutina;
    }

    @Override
    public void verProgreso(Socio socio) {
        System.out.println(String.format("Peso Actual: '%s'", socio.getPesoActual()));
        System.out.println(String.format("Peso Ideal: '%s'", this.getPesoIdeal()));
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

    private void calcularIdeal(Socio socio) {
        float pesoPorAlturaIdeal = (socio.getAltura() - 1 ) * 100;
        if (SexoBiologico.FEMENINO.equals(socio.getSexo())) {
            pesoPorAlturaIdeal *= 0.875f;
        } else {
            pesoPorAlturaIdeal -= 2;
        }
        this.pesoIdeal = Math.round(pesoPorAlturaIdeal);
    }

    public int getPesoIdeal() {
        return this.pesoIdeal;
    }

}
