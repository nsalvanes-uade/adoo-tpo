package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.SexoBiologico;
import edu.ar.uade.servicios.ILoginAdapter;
import edu.ar.uade.servicios.IMedidorAdapter;

import java.util.ArrayList;
import java.util.List;

public class Socio {

    private String userName;
    private int edad;
    private SexoBiologico sexo;
    private float altura;
    private float pesoActual;
    private float masaCorporalActual;
    private float grasaCorporalActual;

    private Objetivo objetivo;
    private int diasDeEntrenamiento;
    private Rutina rutinaDiaria;
    private List<Medicion> mediciones;
    private List<Trofeo> trofeos;

    private ILoginAdapter login;
    private IMedidorAdapter medidor;

    public Socio(String userName, int edad, SexoBiologico sexo, float altura, float pesoActual, float masaCorporalActual,
                 float grasaCorporalActual, Objetivo objetivo, int diasDeEntrenamiento, ILoginAdapter login, IMedidorAdapter medidor) {
        this.userName = userName;
        this.edad = edad;
        this.sexo = sexo;
        this.altura = altura;
        this.pesoActual = pesoActual;
        this.masaCorporalActual = masaCorporalActual;
        this.grasaCorporalActual = grasaCorporalActual;
        this.objetivo = objetivo;
        this.diasDeEntrenamiento = diasDeEntrenamiento;
        this.mediciones = new ArrayList<>();
        this.trofeos = new ArrayList<>();
        this.login = login;
        this.medidor = medidor;
    }

    public void generarRutina() {
        this.objetivo.generarRutina(this);
    }

    public void registrarMedicion() {
        Medicion medicion = this.medidor.obtenerMedicion();
        this.pesoActual = medicion.getPeso();
        this.masaCorporalActual = medicion.getMasaMuscular();
        this.grasaCorporalActual = medicion.getGrasaCorporal();
        this.mediciones.add(medicion);
        Trofeo.chequearPremios(this);
    }

    public void verProgreso() {
        this.getMediciones().forEach(m -> System.out.println(
            String.format("Fecha: '%s' Masa Muscular: '%s' Grasa Corporal: '%s'",
                    m.getFecha(), m.getMasaMuscular(), m.getGrasaCorporal())
        ));
    	if (this.getObjetivo() != null) {
	    	this.getObjetivo().verProgreso(this);
    	} else {
            System.out.println("Aún no tienes un objetivo definido.");
        }
    }

    public void registrarEjercicioCompletado(EjercicioConcretoRealizado ejercicioCompletado) {
        this.rutinaDiaria.getEjerciciosRealizados().add(ejercicioCompletado);
        this.objetivo.revisarObjetivo(this);
        Trofeo.chequearPremios(this);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public SexoBiologico getSexo() {
        return sexo;
    }

    public void setSexo(SexoBiologico sexo) {
        this.sexo = sexo;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(float pesoActual) {
        this.pesoActual = pesoActual;
    }

    public float getMasaCorporalActual() {
        return masaCorporalActual;
    }

    public void setMasaCorporalActual(float masaCorporalActual) {
        this.masaCorporalActual = masaCorporalActual;
    }

    public float getGrasaCorporalActual() {
        return grasaCorporalActual;
    }

    public void setGrasaCorporalActual(float grasaCorporalActual) {
        this.grasaCorporalActual = grasaCorporalActual;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public int getDiasDeEntrenamiento() {
        return diasDeEntrenamiento;
    }

    public void setDiasDeEntrenamiento(int diasDeEntrenamiento) {
        this.diasDeEntrenamiento = diasDeEntrenamiento;
    }

    public Rutina getRutinaDiaria() {
        return rutinaDiaria;
    }

    public void setRutinaDiaria(Rutina rutinaDiaria) {
        this.rutinaDiaria = rutinaDiaria;
    }

    public List<Medicion> getMediciones() {
        return mediciones;
    }

    public void setMediciones(List<Medicion> mediciones) {
        this.mediciones = mediciones;
    }

    public List<Trofeo> getTrofeos() {
        return trofeos;
    }

    public void setTrofeos(List<Trofeo> trofeos) {
        this.trofeos = trofeos;
    }

    public boolean autenticarse(String password) {
        return login.autenticarse(this.getUserName(), password);
    }
}
