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
        UtilidadesValidacion.datosCarrera(configuracionJuego);
        UtilidadesValidacion.vistaListaJugadores(pista);
        UtilidadesValidacion.presionarParaContinuar();
        UtilidadesValidacion.cerrarScanner();
        new IniciarCarrera().empezarCarrera(configuracionJuego, conductoresSelectos);
    }

    private void obtenerNombreCarrera() {
        configuracionJuego.setNombreCarrera(UtilidadesValidacion
                .validarNoVacio(Constantes.INGRESO_NOMBRE_CARRERA));
    }

    private void obtenerPista() {
        conductores = UtilidadesFB.obtenerConductores("conductores");
        obtenerDistanciaTotal();
        obtenerNumeroCarriles();
        agregarCarriles();
        configuracionJuego.setPista(pista);
    }

    private void obtenerDistanciaTotal() {
        pista.setDistanciaTotal(UtilidadesValidacion
                .validarNumerico(Constantes.INGRESO_DISTANCIA_CARRERA));
    }

    private void obtenerNumeroCarriles() {
        pista.setNumeroCarriles(UtilidadesValidacion
                .validarNumerico(Constantes.INGRESO_CANTIDAD_CARRILES));
        limiteCarriles();
    }

    private void limiteCarriles() {
        if (pista.getNumeroCarriles() < 4 || pista.getNumeroCarriles() > conductores.size()) {
            System.out.println(Constantes.MINIMO_MAXIMO_CONDUCTORES + conductores.size());
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
        return UtilidadesValidacion.validarNoVacio(Constantes.INGRESO_COLOR_CARRO + index);
    }

    private String obtenerMarca(int index) {
        return UtilidadesValidacion.validarNoVacio(Constantes.INGRESO_MARCA_CARRO + index);
    }

    private String obtenerModelo(int index) {
        return UtilidadesValidacion.validarNoVacio(Constantes.INGRESO_MODELO_CARRO + index);
    }

    private Carro obtenerCarro(int index) {
        var color = obtenerColor(index);
        var marca = obtenerMarca(index);
        var modelo = obtenerModelo(index);

        return new Carro(color, marca, modelo);
    }
}
