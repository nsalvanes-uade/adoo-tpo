package edu.ar.uade.servicios;

import edu.ar.uade.modelo.enumeradores.SexoBiologico;

public interface ICalculadorIdealExternoAdapter {
    float calcularMasaMuscular(float peso, float altura, SexoBiologico sexo);
    float calcularGrasaCorporal(float peso, float altura, SexoBiologico sexo);
}
