import edu.ar.uade.controlador.EntrenamientoControlador;
import edu.ar.uade.modelo.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestObjetivoBajar extends TestBase {

    @Test
    @Order(1)
    public void testGenerarRutinaObjetivoBajar(){
        controlador.generarRutina(socioEjemplo.getUserName(), EntrenamientoControlador.NombreObjetivo.BAJAR);

        assertEquals(ObjetivoBajar.class, socioEjemplo.getObjetivo().getClass());
        Rutina rutina = socioEjemplo.getObjetivo().getRutina();;
        assertEquals(
            socioEjemplo.getDiasDeEntrenamiento().size()*Rutina.DURACION_SEMANAS,
            rutina.getEntrenamientos().size()
        );
        assertTrue(rutina.getEntrenamientos().stream()
            .filter(
                d -> d.getEjercicios().stream()
                    .filter(
                         e -> e.getNivelAerobico() >= 3
                    ).findAny().isEmpty()
            ).findAny().isEmpty()
        );
        assertTrue(rutina.getEntrenamientos().stream()
            .filter( d -> {
                int duracionDia = d.getEjercicios().stream().mapToInt(e -> e.getMinDuracion()).sum();
                return duracionDia < 60 || duracionDia > 90;
            }).findAny().isEmpty()
        );
    }

    @Test
    @Order(2)
    public void cumplirDiaEntrenamiento() {
        DiaDeEntrenamiento dia = controlador.comenzarEntrenamiento(socioEjemplo.getUserName());
        dia.getEjercicios().forEach(e -> {
            controlador.registrarEjercicioRealizado(
                socioEjemplo.getUserName(),
                e.getNombre(),
                e.getCantidadSeries(),
                e.getRepeticiones(),
                e.getPesoAsignado()
            );
        });
        controlador.finalizarEntrenamiento(socioEjemplo.getUserName());
        assertTrue(dia.isCumplido());
    }

    @Test
    @Order(3)
    public void cumplirRutina() {
        while (!socioEjemplo.getObjetivo().getRutina().isFinalizada()){
            cumplirDiaEntrenamiento();
        }
        assertTrue(socioEjemplo.getObjetivo().getRutina().isCumplida());

        assertTrue(socioEjemplo.getTrofeos().stream().filter(
            t -> t.getNombre().equals("Constancia")
        ).findAny().isPresent());
        assertTrue(outputStreamCaptor.toString().contains("Felicitaciones! Has recibido el trofeo Constancia"));
    }

    //TODO: Test objetivo cumplido + trofeo dedicacion
    //TODO: Tests objetivo mantener, objetivo tonificar

    @Test
    @Order(4)
    public void testMedicion() {
        String userInput = String.format("95%s20%s15",
            System.lineSeparator(),
            System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        controlador.registrarMedicion(socioEjemplo.getUserName());

        Medicion medicion = socioEjemplo.getMediciones().get(0);
        assertEquals(95, medicion.getPeso());
        assertEquals(20, medicion.getMasaMuscular());
        assertEquals(15, medicion.getGrasaCorporal());
    }

    @Test
    @Order(5)
    public void testTrofeoCreido() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        testMedicion();
        testMedicion();

        assertTrue(socioEjemplo.getTrofeos().stream().filter(
            t -> t.getNombre().equals("Creído")
        ).findAny().isPresent());
        assertTrue(outputStreamCaptor.toString().contains("Felicitaciones! Has recibido el trofeo Creído"));
    }

}
