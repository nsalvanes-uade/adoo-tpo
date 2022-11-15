package edu.ar.uade.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TrofeoCreido extends Trofeo {
    public TrofeoCreido() {
        super("Creído", "Trofeo por pesarse más de 3 veces en el mes");
    }

    @Override
    public boolean cumpleCondiciones(Socio contexto) {
    	//REVIEW: evaluar condiciones
    	/*
    	 * obtengo todas las mediciones y las filtro para que queden solo las de este mes
    	 * después las cuento y si son 3 o más devuelvo true.
    	 */
        List<Medicion> medicionesEsteMes =
            contexto.getMediciones()
            	.stream()
                .filter(e -> e.getFecha().getMonthValue() == LocalDate.now().getMonthValue())
                .collect(Collectors.toList());
        if (medicionesEsteMes.size() >= 3)
        	return true;
        return false;
    }
}
