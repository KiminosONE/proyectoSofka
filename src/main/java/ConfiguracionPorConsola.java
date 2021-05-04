import models.*;
import utils.UtilidadesFB;
import utils.UtilidadesValidacion;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionPorConsola {
    Pista pista = new Pista();
    Juego configuracionJuego = new Juego();
    List<Conductor> conductores = new ArrayList<>();
    List<Conductor> conductoresSelectos = new ArrayList<>();
    Boolean usoFirebase;

    void ejecutarJuegoPorConsola(String respuesta) {
        verificarFB(respuesta);
        obtenerConfiguracionJuego();
    }

    private void verificarFB(String respuesta) {
        usoFirebase = "3".equals(respuesta);
    }

    private void obtenerConfiguracionJuego() {
        obtenerNombreCarrera();
        obtenerPista();
        mostrarDatos();
        UtilidadesValidacion.cerrarScanner();
        new IniciarCarrera().empezarCarrera(configuracionJuego, conductoresSelectos);
    }

    private void obtenerNombreCarrera() {
        configuracionJuego.setNombreCarrera(UtilidadesValidacion
                .validarNoVacio("Ingrese el nombre de la carrera"));
    }

    private void obtenerPista() {
        conductores = UtilidadesFB.obtenerConductores("conductores");
        obtenerDistanciaTotal();
        obtenerNumeroCarriles();
        agregarCarriles();
        configuracionJuego.setPista(pista);
    }

    void mostrarDatos() {
        pista.getCarriles().forEach(carril -> System.out.println(carril.getConductor().getCarro().getColor()
                + " => " + carril.getConductor().getId()
                + " => " + carril.getConductor().getNombre()));
    }

    private void obtenerDistanciaTotal() {
        pista.setDistanciaTotal(UtilidadesValidacion
                .validarNumerico("Ingrese la distancia total de la pista en Km"));
    }

    private void obtenerNumeroCarriles() {
        pista.setNumeroCarriles(UtilidadesValidacion
                .validarNumerico("Ingrese la cantidad de carriles que tiene la pista"));
        limiteCarriles();
    }

    private void limiteCarriles() {
        if (pista.getNumeroCarriles() < 4 || pista.getNumeroCarriles() > conductores.size()) {
            System.out.println("El minimo de conductores debe de ser 4 y máximo de " + conductores.size());
            obtenerNumeroCarriles();
        }
    }

    private void agregarCarriles() {
        var carriles = new ArrayList<Carril>();
        for (int i = 0; i < pista.getNumeroCarriles(); i++) {
            var conductor = conductores.get(i);
            if (!usoFirebase) {
                var carro = obtenerCarro(i + 1);
                conductor.setCarro(carro);
            }
            conductoresSelectos.add(conductor);
            carriles.add(new Carril(i + 1, conductor));
        }
        pista.setCarriles(carriles);
    }

    private String obtenerColor(int index) {
        return UtilidadesValidacion.validarNoVacio("Ingresar el color del carro " + index);
    }

    private String obtenerMarca(int index) {
        return UtilidadesValidacion.validarNoVacio("Ingresar la marca del carro " + index);
    }

    private String obtenerModelo(int index) {
        return UtilidadesValidacion.validarNoVacio("Ingresar el modelo del carro " + index);
    }

    private Carro obtenerCarro(int index) {
        var color = obtenerColor(index);
        var marca = obtenerMarca(index);
        var modelo = obtenerModelo(index);

        return new Carro(color, marca, modelo);
    }
}
