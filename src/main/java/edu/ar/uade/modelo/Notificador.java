package edu.ar.uade.modelo;

import edu.ar.uade.servicios.INotificadorPushAdapter;
import edu.ar.uade.servicios.NotificadorPushAdaptadoFirebase;

public class Notificador {
    private INotificadorPushAdapter notificadorPush;

    public Notificador() {
        this.notificadorPush = new NotificadorPushAdaptadoFirebase();
    }

    public void enviarNotificacion(Socio socio, String mensaje) {
        this.notificadorPush.notificar(socio.getUserName(), mensaje);
    }
}
