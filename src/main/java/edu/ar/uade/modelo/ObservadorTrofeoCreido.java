package edu.ar.uade.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ObservadorTrofeoCreido extends ObservadorTrofeo implements IObserverSocio {
    public ObservadorTrofeoCreido(Socio socio) {
        super(socio);
    }

    @Override
    public void notificarMedicion(Socio socio) {
        List<Medicion> medicionesEsteMes =
            socio.getMediciones()
                .stream()
                .filter(e -> e.getFecha().getMonthValue() == LocalDate.now().getMonthValue())
                .collect(Collectors.toList());
        if (medicionesEsteMes.size() >= 3) {
            this.otorgar(new TrofeoCreido());
        };
    }

}
