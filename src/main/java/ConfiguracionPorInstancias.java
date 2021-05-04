import models.Carril;
import models.Juego;
import models.Pista;
import utils.UtilidadesFB;
import utils.UtilidadesValidacion;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionPorInstancias {
    void ejecutarJuegoPorInstancias() {
        List<Carril> carriles = new ArrayList<>();
        var conductores = UtilidadesFB.obtenerConductores("conductores");
        conductores.forEach(conductor -> {
            var index = conductores.indexOf(conductor);
            carriles.add(new Carril(index + 1, conductores.get(index)));
        });

        var pista = new Pista(carriles, 4, conductores.size());

        Juego configuracionJuego = new Juego(pista, "Carrera por instancias");

        UtilidadesValidacion.datosCarrera(configuracionJuego);
        UtilidadesValidacion.vistaListaJugadores(pista);

        UtilidadesValidacion.presionarParaContinuar();
        new IniciarCarrera().empezarCarrera(configuracionJuego, conductores);
    }
}
