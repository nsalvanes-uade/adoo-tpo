package edu.ar.uade.modelo;

public class TrofeoDedicacion extends Trofeo {
    public TrofeoDedicacion() {
        super("Dedicación", "Trofeo por cumplir su objetivo");
    }

    @Override
    public boolean cumpleCondiciones(Socio contexto) {
    	contexto.getObjetivo().revisarObjetivo(contexto);
        return contexto.getObjetivo().isCumplido();
    }
}
