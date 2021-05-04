import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IniciarCarrera {
    Podio podio = new Podio();
    Map<String, Integer> listaPosicion = new HashMap<>();
    Juego configuracion;

    void empezarCarrera(Juego configuracionjuego, List<Carro> carros) {
        podio.setConductores(new ArrayList<>());
//        obtenerListaDeCarros(carros);
        configuracion = configuracionjuego;
//        obtenerGanadores(carros, podio.getConductores());
        imprimirGanadores();
    }

    private void imprimirGanadores() {
        podio.getConductores().forEach(conductor -> {
            var index = podio.getConductores().indexOf(conductor) + 1;
            System.out.println(index + "). " + conductor.getNombre());
        });
    }
/*

    private void obtenerListaDeCarros(List<Carro> carros) {
        carros.forEach(carro -> listaPosicion.put(carro.getConductor().getNombre(), 0));
    }

    private void obtenerGanadores(List<Carro> carros, List<Conductor> listaPodio) {
        while (listaPodio.size() < 3) {
            for (int i = 0; i < obtenerTamannoListaCarriles(); i++) {
                var conductor = carros.get(i).getConductor();
                var llave = conductor.getNombre();
                var posicion = listaPosicion.get(llave);
                listaPosicion.replace(llave, movimiento(posicion));
                agregarGanador(listaPodio, conductor, posicion);
            }
        }

        podio.setConductores(listaPodio);
    }
*/

    private void agregarGanador(List<Conductor> listaPodio, Conductor conductor, Integer posicion) {
        if (posicion >= obtenerDistanciaTotal() && !(listaPodio.contains(conductor)) && listaPodio.size() < 3) {
            listaPodio.add(conductor);
        }
    }

    private Integer obtenerTamannoListaCarriles() {
        return configuracion.getPista().getCarriles().size();
    }

    private Integer obtenerDistanciaTotal() {
        return configuracion.getPista().getDistanciaTotal() * 1000;
    }

    private Integer movimiento(Integer posicion) {
        var dado = (int) Math.ceil(Math.random() * 6);
        return (dado * 100) + posicion;
    }

}
