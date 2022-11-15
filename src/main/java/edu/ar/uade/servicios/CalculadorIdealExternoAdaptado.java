package edu.ar.uade.servicios;

import edu.ar.uade.modelo.enumeradores.SexoBiologico;

public class CalculadorIdealExternoAdaptado implements ICalculadorIdealExternoAdapter {

    //TODO: cómo resolver?
    @Override
    public float calcularMasaMuscular(float peso, float altura, SexoBiologico sexo) {
        return 0;
    }

    @Override
    public float calcularGrasaCorporal(float peso, float altura, SexoBiologico sexo) {
        return 0;
    }
}
