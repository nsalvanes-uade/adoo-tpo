package edu.ar.uade.modelo;

import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;
import edu.ar.uade.modelo.enumeradores.GrupoMuscular;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiaDeEntrenamiento {

    private List<Ejercicio> ejercicios;
    //Se utiliza una lista de ejercicios realizados para facilitar implementación
    private List<EjercicioRealizado> ejerciciosRealizados;

    private LocalDate fechaRealizacion;
    private boolean finalizado = false;

    public DiaDeEntrenamiento() {
        this.ejercicios = new ArrayList<>();
        this.ejerciciosRealizados = new ArrayList<>();
    }

    public void iniciar() {
        fechaRealizacion = LocalDate.now();
    }

    public void finalizar() {
        finalizado = true;
    }

    public boolean isCumplido() {
        return ejerciciosRealizados.size()==ejercicios.size()
            && ejerciciosRealizados.stream().filter(e -> !e.isCumplido()).findAny().isEmpty();
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public List<EjercicioRealizado> getEjerciciosRealizados() {
        return ejerciciosRealizados;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void generarEjercicios(int nivelAerobicoMinimo,
                                  RangoNumerico duracionEntrenamiento) {
        Stream<Ejercicio> ejerciciosFiltrados =  CatalogoEjercicios.getInstancia().getEjerciciosDisponibles()
                .stream()
                .filter(e -> e.getNivelAerobico()>=nivelAerobicoMinimo);
        calcularCombinacionEjercicios(ejerciciosFiltrados, duracionEntrenamiento);
    }

    public void generarEjercicios(int nivelAerobicoMaximo,
                                  List<ExigenciaMuscular> exigenciasPermitidas,
                                  RangoNumerico duracionEntrenamiento) {
        Stream<Ejercicio> ejerciciosFiltrados =  CatalogoEjercicios.getInstancia().getEjerciciosDisponibles()
                .stream()
                .filter(e -> e.getNivelAerobico()<=nivelAerobicoMaximo)
                .filter(e -> !exigenciasPermitidas.contains(e.getNivelMuscular()));
        calcularCombinacionEjercicios(ejerciciosFiltrados, duracionEntrenamiento);
    }

    public void generarEjercicios(RangoNumerico rangoNivelAerobicoPermitido,
                                  List<ExigenciaMuscular> exigenciasPermitidas,
                                  RangoNumerico duracionEntrenamiento) {
        Stream<Ejercicio> ejerciciosFiltrados =  CatalogoEjercicios.getInstancia().getEjerciciosDisponibles()
                .stream()
                .filter(e -> rangoNivelAerobicoPermitido.incluye(e.getNivelAerobico()))
                .filter(e -> !exigenciasPermitidas.contains(e.getNivelMuscular()));
        calcularCombinacionEjercicios(ejerciciosFiltrados, duracionEntrenamiento);
    }

    private void calcularCombinacionEjercicios(Stream<Ejercicio> ejerciciosFiltrados, RangoNumerico duracionEntrenamiento) {
        //Se agrupan ejercicios disponibles por músculo
        Map<GrupoMuscular, List<Ejercicio>> ejerciciosAgrupados = ejerciciosFiltrados
                .map(Ejercicio::new)
                .collect(Collectors.groupingBy(Ejercicio::getGrupo));

        //Se crean iteradores para facilitar el bucle de creacion de rutina con ejercicios combinados hasta alcanzar duración
        Map<GrupoMuscular, Iterator<Ejercicio>> iteradorEjerciciosAgrupado = new HashMap<>();
        Iterator<GrupoMuscular> iteradorDeGrupo = null;

        int duracionActual = 0;

        while (duracionActual < duracionEntrenamiento.getMin()) {
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

            if (duracionActual + ejericioDisponible.getMinDuracion() < duracionEntrenamiento.getMax()) {
                ejercicios.add(ejericioDisponible);
                duracionActual += ejericioDisponible.getMinDuracion();
            }
        }
    }

    @Override
    public String toString() {
        return "DiaDeEntrenamiento{\n" +
                "\tejercicios=" + ejercicios +
                "\n}";
    }

}
