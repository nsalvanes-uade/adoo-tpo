package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.SexoBiologico;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ObjetivoBajar extends Objetivo {

    private int pesoIdeal;

    public ObjetivoBajar(String descripcion) {
        super(descripcion);
    }

    @Override
    public void revisarObjetivo(Socio socio) {
        if(socio.getPesoActual()<=pesoIdeal){
            this.marcarCumplido();
            this.getNotificador().enviarNotificacion(
                socio,
                "Felicitaciones! Objetivo bajar de peso cumplido. Sugerimos cambiar objetivo a 'Mantener Figura'"
            );
        }
    }

    @Override
    public void generarRutina(Socio socio) {
        calcularIdeal(socio.getPesoActual(), socio.getAltura(), socio.getSexo());
        List<EjercicioConcreto> ejerciciosDisponibles =
            CatalogoEjercicios.getInstancia().getEjerciciosDisponibles()
                .stream()
                .filter(e -> e.getNivelAerobico()>=3)
                .map(EjercicioConcreto::new)
                .collect(Collectors.toList());
        //TODO: Como limitar el tiempo de cada día de entrenamiento a entre 1 y 1 hora y media?
        socio.setRutinaDiaria(new Rutina(Collections.unmodifiableList(ejerciciosDisponibles)));
    }

    private void calcularIdeal(float pesoActual, float alturaActual, SexoBiologico sexo) {
        //TODO: Como resolver? Debería hacerlo un sistema externo igual que en ObjetivoTonificar?
        this.pesoIdeal = this.pesoIdeal-5;
    }

}
