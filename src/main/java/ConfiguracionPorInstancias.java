import models.Carril;
import models.Juego;
import models.Pista;
import utils.UtilidadesFB;

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

        Juego configuracionJuego =
                new Juego(new Pista(carriles, 4, 6), "Carrera por instancias");
        new IniciarCarrera().empezarCarrera(configuracionJuego, conductores);
    }
}
