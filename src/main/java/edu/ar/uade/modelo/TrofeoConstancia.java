package edu.ar.uade.modelo;

public class TrofeoConstancia extends Trofeo {
    public TrofeoConstancia() {
        super("Constancia", "Trofeo por cumplir a la perfección las rutinas");
    }

    @Override
    public boolean cumpleCondiciones(Socio contexto) {
        //TODO: evaluar condiciones
        return false;
    }
}
