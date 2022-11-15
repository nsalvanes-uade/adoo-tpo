package edu.ar.uade.modelo;

public class TrofeoCreido extends Trofeo {
    public TrofeoCreido() {
        super("Creído", "Trofeo por pesarse más de 3 veces en el mes");
    }

    @Override
    public boolean cumpleCondiciones(Socio contexto) {
        //TODO: evaluar condiciones
        return false;
    }
}
