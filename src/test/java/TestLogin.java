import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogin extends TestBase {

    @Test
    public void testLogin(){
        assertNull(controlador.login(socioEjemplo.getUserName(), "ABCDEF"));

        assertEquals(socioEjemplo, controlador.login(socioEjemplo.getUserName(), "123456"));
    }

}
