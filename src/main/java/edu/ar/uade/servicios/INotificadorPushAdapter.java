package edu.ar.uade.servicios;

import edu.ar.uade.modelo.Socio;

public interface INotificadorPushAdapter {
    public void notificar(String userName, String mensaje);
}
