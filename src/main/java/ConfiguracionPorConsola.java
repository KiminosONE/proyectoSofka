import models.*;
import utils.UtilidadesFB;
import utils.UtilidadesValidacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfiguracionPorConsola {
    Pista pista = new Pista();
    Juego configuracionJuego = new Juego();
    List<Carro> carros = new ArrayList<>();
    List<Conductor> conductores = new ArrayList<>();
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
        // new IniciarCarrera().empezarCarrera(configuracionJuego, carros);
    }

    private void obtenerNombreCarrera() {
        configuracionJuego.setNombreCarrera(UtilidadesValidacion.validarNoVacio("Ingrese el nombre de la carrera"));
    }

    private void obtenerPista() {
        obtenerDistanciaTotal();
        obtenerNumeroCarriles();
        conductores = UtilidadesFB.obtenerConductores("conductores");
        agregarCarriles();
        configuracionJuego.setPista(pista);
    }

    void mostrarDatos() {
        pista.getCarriles().forEach(carril -> {
            System.out.println(carril.getConductor().getCarro().getColor() + " => " + carril.getConductor().getId());
        });
    }

    private void obtenerDistanciaTotal() {
        pista.setDistanciaTotal(UtilidadesValidacion.validarNumerico("Ingrese la distancia total de la pista en Km"));
    }

    private void obtenerNumeroCarriles() {
        pista.setNumeroCarriles(UtilidadesValidacion.validarNumerico("Ingrese la cantidad de carriles que tiene la pista"));
    }

    private void agregarCarriles() {
        var carriles = new ArrayList<Carril>();
        for (int i = 1; i <= pista.getNumeroCarriles(); i++) {
            var conductor = conductores.get(i);
            if (!usoFirebase) {
                var carro = obtenerCarro(i);
                conductor.setCarro(carro);
            }
            carriles.add(new Carril(i, conductor));
        }
        pista.setCarriles(carriles);
    }

//    private Conductor obtenerConductor(Conductor conductor) {
//        return new Conductor(conductor.getId(), conductor.getNombre(), conductor.getCarro());
//    }

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
/*
    private Conductor obtenerConductor() {
        var nombre = UtilidadesValidacion.validarNoVacio("Ingrese el nombre del conductor");
        return new Conductor(nombre);
    }*/
}
