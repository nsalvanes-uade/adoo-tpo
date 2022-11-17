package edu.ar.uade.servicios;

import edu.ar.uade.modelo.enumeradores.SexoBiologico;

public class CalculadorIdealExternoAdaptado implements ICalculadorIdealExternoAdapter {

	/*
	 * Random valores sacados de google para testear pero en el futuro se reemplazaría por la implementación que permita obtener los valores 
	 * del servicio externo que en este momento no sabemos cuál es.
	 */
    @Override
    public float calcularMasaMuscular(float peso, float altura, SexoBiologico sexo) {
    	if (SexoBiologico.MASCULINO.equals(sexo))
    		return 0.4f;
    	else
    		return 0.3f;
    }

    @Override
    public float calcularGrasaCorporal(float peso, float altura, SexoBiologico sexo) {
		if (SexoBiologico.MASCULINO.equals(sexo))
    		return 0.1f;
    	else
    		return 0.2f;
    }
}
