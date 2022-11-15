package edu.ar.uade.servicios;

import edu.ar.uade.modelo.Medicion;

import java.util.Scanner;

public class MedidorAdaptado implements IMedidorAdapter {
    @Override
    public Medicion obtenerMedicion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese peso actual: ");
        float peso = scanner.nextFloat();
        System.out.println("Ingrese masa muscular actual: ");
        float masa = scanner.nextFloat();
        System.out.println("Ingrese grasa corporal actual: ");
        float grasa = scanner.nextFloat();
        return new Medicion(peso, masa, grasa);
    }
}
