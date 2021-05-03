import models.*;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionPorConsola {
    Pista pista = new Pista();
    Juego configuracionJuego = new Juego();
    List<Carro> carros = new ArrayList<>();

    void ejecutarJuegoPorConsola() {
        obtenerConfiguracionJuego();
    }

    private void obtenerConfiguracionJuego() {
        obtenerNombreCarrera();
        obtenerPista();
    }

    private void obtenerNombreCarrera() {
        configuracionJuego.setNombreCarrera(Utilidades.validarNoVacio("Ingrese el nombre de la carrera"));
    }

    private void obtenerPista() {
        obtenerDistanciaTotal();
        obtenerNumeroCarriles();
        agregarCarriles();
        configuracionJuego.setPista(pista);
    }

    private void obtenerDistanciaTotal() {
        pista.setDistanciaTotal(Utilidades.validarNumerico("Ingrese la distancia total de la pista en Km"));
    }

    private void obtenerNumeroCarriles() {
        pista.setNumeroCarriles(Utilidades.validarNumerico("Ingrese la cantidad de carriles que tiene la pista"));
    }

    private void agregarCarriles() {
        var carriles = new ArrayList<Carril>();
        for (int i = 1; i <= pista.getNumeroCarriles(); i++) {
            var carro = obtenerCarro(i);
            carros.add(carro);
            carriles.add(new Carril(i, carro));
        }
        pista.setCarriles(carriles);
    }

    private Carro obtenerCarro(int index) {
        var color = obtenerColor(index);
        var marca = obtenerMarca(index);
        var modelo = obtenerModelo(index);
        var conductor = obtenerConductor();

        return new Carro(color, marca, modelo, conductor);
    }

    private String obtenerColor(int index) {
        return Utilidades.validarNoVacio("Ingresar el color del carro " + index);
    }

    private String obtenerMarca(int index) {
        return Utilidades.validarNoVacio("Ingresar la marca del carro " + index);
    }

    private String obtenerModelo(int index) {
        return Utilidades.validarNoVacio("Ingresar el modelo del carro " + index);
    }

    private Conductor obtenerConductor() {
        var nombre = Utilidades.validarNoVacio("Ingrese el nombre del conductor");
        return new Conductor(nombre);
    }
}
