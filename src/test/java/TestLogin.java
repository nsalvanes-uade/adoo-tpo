import edu.ar.uade.modelo.ObjetivoBajar;
import edu.ar.uade.modelo.Socio;
import edu.ar.uade.modelo.enumeradores.SexoBiologico;
import edu.ar.uade.servicios.LoginAdaptado;
import edu.ar.uade.servicios.MedidorAdaptado;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin {

    private static Socio socioEjemplo;

    @BeforeAll
    static void setup() {
        socioEjemplo = new Socio(
            "johnjohn", 30, SexoBiologico.MASCULINO,
            1.82f, 101.f, 22.f, 5.6f,
            new ObjetivoBajar(), 3,  new LoginAdaptado(), new MedidorAdaptado()
        );
    }

    @Test
    public void testAutenticarseConError() {
        assertFalse(socioEjemplo.autenticarse("654321"));
    }

    @Test
    public void testAutenticarseConExito() {
        assertTrue(socioEjemplo.autenticarse("123456"));
    }

}
