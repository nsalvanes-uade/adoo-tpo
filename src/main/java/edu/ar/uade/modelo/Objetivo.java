package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.GrupoMuscular;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Objetivo {

    private String descripcion;
    private int minDuracionDiaEntrenamiento;
    private int maxDuracionDiaEntrenamiento;

    private boolean cumplido;
    private Rutina rutina;
    private Notificador notificador;

    private List<IObserverObjetivo> observadores;

    public Objetivo(String descripcion, int minDuracionDiaEntrenamiento, int maxDuracionDiaEntrenamiento) {
        this.descripcion = descripcion;
        this.minDuracionDiaEntrenamiento = minDuracionDiaEntrenamiento;
        this.maxDuracionDiaEntrenamiento = maxDuracionDiaEntrenamiento;
        this.cumplido = false;
        this.notificador = new Notificador();
        this.observadores = new ArrayList<>();
    }

    public void generarRutina(Socio socio) {
        calcularIdeal(socio);
        Stream<Ejercicio> ejerciciosFiltrados = filtrarEjercicios(
            CatalogoEjercicios.getInstancia().getEjerciciosDisponibles().stream()
        );
        this.rutina = new Rutina(
            this.calcularEntrenamiento(
                socio.getDiasDeEntrenamiento().size(),
                ejerciciosFiltrados
            )
        );
        this.rutina.agregarObservador(new ObservadorTrofeoConstancia(socio));
    }

    protected abstract void calcularIdeal(Socio socio);
    protected abstract Stream<Ejercicio> filtrarEjercicios(Stream<Ejercicio> ejerciciosDisponibles);

    private List<DiaDeEntrenamiento> calcularEntrenamiento(int cantidadDias, Stream<Ejercicio> ejerciciosFiltrados) {
        //Se agrupan ejercicios disponibles por músculo
        Map<GrupoMuscular, List<Ejercicio>> ejerciciosAgrupados = ejerciciosFiltrados
                .map(Ejercicio::new)
                .collect(Collectors.groupingBy(Ejercicio::getGrupo));

        //Se crean iteradores para facilitar el bucle de creacion de rutina con ejercicios combinados hasta alcanzar duración
        Map<GrupoMuscular, Iterator<Ejercicio>> iteradorEjerciciosAgrupado = new HashMap<>();
        Iterator<GrupoMuscular> iteradorDeGrupo = null;

        List<DiaDeEntrenamiento> diasEntrenamiento = new ArrayList<>();
        for(int i = 0; i<cantidadDias*Rutina.DURACION_SEMANAS ; i++) {

            List<Ejercicio> ejercicios = new ArrayList<>();
            int duracionActual = 0;

            while (duracionActual < minDuracionDiaEntrenamiento) {
                //Inicializa o reinicia iterador de grupo muscular
                if (iteradorDeGrupo == null || !iteradorDeGrupo.hasNext()) {
                    iteradorDeGrupo = Arrays.stream(GrupoMuscular.values()).iterator();
                }
                GrupoMuscular grupoMuscular = iteradorDeGrupo.next();
                Iterator<Ejercicio> iteradorEjercicios = iteradorEjerciciosAgrupado.get(grupoMuscular);

                //Inicializa o reinicia iterador de ejercicios para grupo muscular
                if (iteradorEjercicios == null || !iteradorEjercicios.hasNext()) {
                    if(!ejerciciosAgrupados.containsKey(grupoMuscular)){
                        continue;
                    }
                    iteradorEjercicios = ejerciciosAgrupados.get(grupoMuscular).iterator();
                    iteradorEjerciciosAgrupado.put(grupoMuscular, iteradorEjercicios);
                }
                Ejercicio ejericioDisponible = iteradorEjercicios.next();

                if (duracionActual + ejericioDisponible.getMinDuracion() < maxDuracionDiaEntrenamiento) {
                    ejercicios.add(ejericioDisponible);
                    duracionActual += ejericioDisponible.getMinDuracion();
                }
            }
            diasEntrenamiento.add(new DiaDeEntrenamiento(ejercicios));
        }
        return diasEntrenamiento;
    }

    public abstract void revisarObjetivo(Socio socio);
    public abstract void verProgreso(Socio socio);

    public void agregarObservador(IObserverObjetivo observador) {
        this.observadores.add(observador);
    }

    public void eliminarObservador(IObserverObjetivo observador) {
        this.observadores.remove(observador);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCumplido() {
        return cumplido;
    }

    protected void marcarCumplido() {
        this.cumplido = true;
        this.observadores.forEach(o -> o.notificarObjetivoCumplido(this));
    }

    public Rutina getRutina() {
        return rutina;
    }

    public Notificador getNotificador() {
        return notificador;
    }
}
