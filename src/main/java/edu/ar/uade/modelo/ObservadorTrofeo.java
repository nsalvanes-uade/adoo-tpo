package edu.ar.uade.modelo;

public abstract class ObservadorTrofeo {

    private Socio socio;

    private Notificador notificador = new Notificador();

    public ObservadorTrofeo(Socio socio) {
        this.socio = socio;
    }

    protected void otorgar(Trofeo trofeo) {
        if(!this.socio.getTrofeos().contains(trofeo)) {
            this.socio.getTrofeos().add(trofeo);
            notificador.enviarNotificacion(
                    socio,
                    String.format("Felicitaciones! Has recibido el trofeo %s", trofeo.getNombre())
            );
        }
    }

}
