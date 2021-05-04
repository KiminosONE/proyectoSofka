import models.Carril;
import models.Carro;
import models.Juego;
import models.Pista;
import utils.UtilidadesFB;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionPorInstancias {
    void ejecutarJuegoPorInstancias() {
        List<Carril> carriles = new ArrayList<>();
        List<Carro> carros = new ArrayList<>();
        UtilidadesFB.obtenerConductores("conductores");
        Juego configuracionJuego = new Juego(new Pista(carriles, 4, 4), "Carrera por instancias");
//        new IniciarCarrera().empezarCarrera(configuracionJuego, carros);
    }
}
