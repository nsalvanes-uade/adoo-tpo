package edu.ar.uade.modelo;

public class ObservadorTrofeoConstancia extends ObservadorTrofeo implements IObserverRutina {

    public ObservadorTrofeoConstancia(Socio socio) {
        super(socio);
    }

    @Override
    public void notificarEntrenamientoFinalizado(Rutina rutina) {
        if(rutina.isCumplida()){
            this.otorgar(new TrofeoConstancia());
        }
    }

}
