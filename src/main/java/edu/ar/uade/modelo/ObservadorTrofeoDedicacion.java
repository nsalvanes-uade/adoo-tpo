package edu.ar.uade.modelo;

public class ObservadorTrofeoDedicacion extends ObservadorTrofeo implements IObserverObjetivo {
    public ObservadorTrofeoDedicacion(Socio socio) {
        super(socio);
    }

    @Override
    public void notificarObjetivoCumplido(Objetivo objetivo) {
        if(objetivo.isCumplido()) {
            this.otorgar(new TrofeoDedicacion());
        }
    }

}
