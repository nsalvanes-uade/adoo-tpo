import edu.ar.uade.controlador.EntrenamientoControlador;
import edu.ar.uade.modelo.*;
import edu.ar.uade.modelo.enumeradores.DiaSemana;
import edu.ar.uade.modelo.enumeradores.ExigenciaMuscular;
import edu.ar.uade.modelo.enumeradores.GrupoMuscular;
import edu.ar.uade.modelo.enumeradores.SexoBiologico;
import edu.ar.uade.servicios.LoginAdaptado;
import edu.ar.uade.servicios.MedidorAdaptado;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class TestBase {

    protected static EntrenamientoControlador controlador;
    protected static Socio socioEjemplo;
    protected static ByteArrayOutputStream outputStreamCaptor;

    @BeforeAll
    static void setup() {
        cargarCatalogoEjercicios();
        socioEjemplo = new Socio(
            "johnjohn", 30, SexoBiologico.MASCULINO,
            1.82f, 101.f, 22.f, 5.6f,
            null, Arrays.asList(DiaSemana.LUN, DiaSemana.MIE, DiaSemana.SAB),
            new LoginAdaptado(), new MedidorAdaptado()
        );
        controlador = new EntrenamientoControlador(Arrays.asList(socioEjemplo));
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    private static void cargarCatalogoEjercicios() {
        CatalogoEjercicios.getInstancia()
                .agregarEjercicio(new Ejercicio("Biceps", GrupoMuscular.BRAZOS, 3, 10, 8f,
                        2, ExigenciaMuscular.MEDIO, 15, new EjercicioVideo("file://ejercicios/biceps.mp4")))
                .agregarEjercicio(new Ejercicio("Escalador", GrupoMuscular.PIERNAS, 3, 12, 10f,
                        5, ExigenciaMuscular.ALTO, 20, new EjercicioVideo("file://ejercicios/escalador.mp4")))
                .agregarEjercicio(new Ejercicio("Abdominales", GrupoMuscular.PECHO, 2, 20, 10f,
                        4, ExigenciaMuscular.MEDIO, 15, new EjercicioVideo("file://ejercicios/abdominales.mp4")))
                .agregarEjercicio(new Ejercicio("Remo", GrupoMuscular.ESPALDA, 4, 10, 5f,
                        2, ExigenciaMuscular.BAJO, 30, new EjercicioVideo("file://ejercicios/remo.mp4")))
                .agregarEjercicio(new Ejercicio("Press", GrupoMuscular.HOMBROS, 2, 20, 20f,
                        1, ExigenciaMuscular.MEDIO, 10, new EjercicioVideo("file://ejercicios/press.mp4")));
    }

}
