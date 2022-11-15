package edu.ar.uade.servicios;

public class NotificadorPushAdaptadoFirebase implements INotificadorPushAdapter {
    @Override
    public void notificar(String userName, String mensaje) {
        System.out.println(
            String.format("Notificación push '%s' enviada a usuario %s", mensaje, userName)
        );
    }
}
