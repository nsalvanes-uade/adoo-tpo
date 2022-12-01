package edu.ar.uade.modelo;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Trofeo {

    private String nombre;
    private String descripcion;
    private LocalDate fecha;

    public Trofeo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
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
        return nombre.equals(trofeo.nombre) && descripcion.equals(trofeo.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion);
    }
}
