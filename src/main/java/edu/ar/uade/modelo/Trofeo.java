package edu.ar.uade.modelo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public abstract class Trofeo {

    private String nombre;
    private LocalDate fecha;
    private String descripcion;

    private static Notificador notificador = new Notificador();

    public Trofeo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.fecha = LocalDate.now();
        this.descripcion = descripcion;
    }

    public abstract boolean cumpleCondiciones(Socio socio);

    public static void chequearPremios(Socio socio) {
        Arrays.asList(
            new TrofeoConstancia(),
            new TrofeoCreido(),
            new TrofeoDedicacion()
        ).forEach(trofeo -> {
            if(!socio.getTrofeos().contains(trofeo) && trofeo.cumpleCondiciones(socio)){
                socio.getTrofeos().add(trofeo);
                notificador.enviarNotificacion(
                    socio,
                    String.format("Felicitaciones! Has recibido el trofeo %s", trofeo.getNombre())
                );
            }
        });
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trofeo trofeo = (Trofeo) o;
        return nombre.equals(trofeo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
