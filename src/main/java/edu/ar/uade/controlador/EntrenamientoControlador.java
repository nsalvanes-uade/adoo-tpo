package edu.ar.uade.controlador;

import edu.ar.uade.modelo.*;
import edu.ar.uade.servicios.CalculadorIdealExternoAdaptado;

import java.util.List;
import java.util.Optional;

public class EntrenamientoControlador {

    private List<Socio> socios;

    public EntrenamientoControlador(List<Socio> socios) {
        this.socios = socios;
    }

    public Socio login(String userName, String password) {
        Optional<Socio> busquedaSocio = buscarSocio(userName);
        if(busquedaSocio.isPresent()){
            Socio socio = busquedaSocio.get();
            if(socio.autenticarse(password)){
                return socio;
            }
        }
        return null;
    }

    public Rutina generarRutina(String userName, NombreObjetivo nuevoObjetivo, Object... parametrosObjetivo) {
        Optional<Socio> busquedaSocio = buscarSocio(userName);
        if(busquedaSocio.isPresent()){
            Socio socio = busquedaSocio.get();
            switch (nuevoObjetivo) {
                case BAJAR -> socio.setObjetivo(new ObjetivoBajar());
                case MANTENER -> socio.setObjetivo(new ObjetivoMantener((int) parametrosObjetivo[0]));
                case TONIFICAR -> socio.setObjetivo(new ObjetivoTonificar(new CalculadorIdealExternoAdaptado()));
            }
            socio.generarRutina();
            return socio.getObjetivo().getRutina();
        }
        return null;
    }

    public void registrarMedicion(String userName) {
        Optional<Socio> busquedaSocio = buscarSocio(userName);
        if(busquedaSocio.isPresent()){
            busquedaSocio.get().registrarMedicion();
        }
    }

    public DiaDeEntrenamiento comenzarEntrenamiento(String userName) {
        Optional<Socio> busquedaSocio = buscarSocio(userName);
        if(busquedaSocio.isPresent()){
            return busquedaSocio.get().comenzarEntrenamientoDiario();
        }
        return null;
    }

    public void registrarEjercicioRealizado(String userName, String nombreEjercicio, int series, int repeticiones, float peso) {
        Optional<Socio> busquedaSocio = buscarSocio(userName);
        if(busquedaSocio.isPresent()) {
            Socio socio = busquedaSocio.get();
            Optional<Ejercicio> busquedaEjercicio = socio.getObjetivo().getRutina()
                    .getUltimoEntrenamientoComenzado()
                    .getEjercicios().stream()
                    .filter(e -> e.getNombre().equals(nombreEjercicio))
                    .findFirst();
            if(busquedaEjercicio.isPresent()){
                EjercicioRealizado ejercicioRealizado = new EjercicioRealizado(
                    busquedaEjercicio.get(),
                    series,
                    repeticiones,
                    peso
                );
                socio.registrarEjercicioRealizado(ejercicioRealizado);
            }
        }
    }

    public void finalizarEntrenamiento(String userName) {
        Optional<Socio> busquedaSocio = buscarSocio(userName);
        if(busquedaSocio.isPresent()){
            busquedaSocio.get().finalizarEntrenamientoDiario();
        }
    }

    private Optional<Socio> buscarSocio(String userName) {
        return socios.stream().filter(s -> s.getUserName().equals(userName)).findFirst();
    }

    public enum NombreObjetivo {
        BAJAR, TONIFICAR, MANTENER;
    }

}
