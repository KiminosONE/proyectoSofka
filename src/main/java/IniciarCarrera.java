import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IniciarCarrera {
    Podio podio = new Podio();
    Map<String, Integer> listaPosicion = new HashMap<>();
    Juego configuracion;

    void empezarCarrera(Juego configuracionjuego, List<Conductor> conductores) {
        podio.setConductores(new ArrayList<>());
        obtenerListaDeCarros(conductores);
        configuracion = configuracionjuego;
        obtenerGanadores(conductores, podio.getConductores());
        imprimirGanadores();
    }

    private void imprimirGanadores() {
        podio.getConductores().forEach(conductor -> {
            var index = podio.getConductores().indexOf(conductor) + 1;
            System.out.println(index + "). " + conductor.getNombre());
        });
    }

    private void obtenerListaDeCarros(List<Conductor> conductores) {
        conductores.forEach(conductor -> listaPosicion.put(conductor.getNombre(), 0));
    }

    private void obtenerGanadores(List<Conductor> conductors, List<Conductor> listaPodio) {
        while (listaPodio.size() < 3) {
            for (int i = 0; i < obtenerTamannoListaCarriles(); i++) {
                var conductor = conductors.get(i);
                var llave = conductor.getNombre();
                var posicion = listaPosicion.get(llave);
                listaPosicion.replace(llave, movimiento(posicion));
                agregarGanador(listaPodio, conductor, posicion);
            }
        }

        podio.setConductores(listaPodio);
    }

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
