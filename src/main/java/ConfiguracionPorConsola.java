import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import connection.ConexionFirebase;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ConfiguracionPorConsola {
    Pista pista = new Pista();
    Juego configuracionJuego = new Juego();
    List<Carro> carros = new ArrayList<>();
    List<Conductor> conductors = new ArrayList<>();
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
        Utilidades.cerrarScanner();
        new IniciarCarrera().empezarCarrera(configuracionJuego, carros);
    }

    private void obtenerNombreCarrera() {
        configuracionJuego.setNombreCarrera(Utilidades.validarNoVacio("Ingrese el nombre de la carrera"));
    }

    private void obtenerPista() {
        obtenerDistanciaTotal();
        obtenerNumeroCarriles();
        if (usoFirebase){
            obtenerConductoresFB();
        }
        agregarCarriles();
        configuracionJuego.setPista(pista);
    }

    private void obtenerDistanciaTotal() {
        pista.setDistanciaTotal(Utilidades.validarNumerico("Ingrese la distancia total de la pista en Km"));
    }

    private void obtenerNumeroCarriles() {
        pista.setNumeroCarriles(Utilidades.validarNumerico("Ingrese la cantidad de carriles que tiene la pista"));
    }

    private void obtenerConductoresFB() {
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = ConexionFirebase.db.collection("conductores").get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.getData());
            conductors.add(new Conductor(document.getId(), document.getData().get("Nombre").toString()));
        }
    }

    private void agregarCarriles() {
        var carriles = new ArrayList<Carril>();
        for (int i = 1; i <= pista.getNumeroCarriles(); i++) {
            var carro = obtenerCarro(i, conductors.get(i));
            carros.add(carro);
            carriles.add(new Carril(i, carro));
        }
        pista.setCarriles(carriles);
    }

    private Carro obtenerCarro(int index, Conductor conductor) {
        var color = obtenerColor(index);
        var marca = obtenerMarca(index);
        var modelo = obtenerModelo(index);
        if (!usoFirebase){
            conductor = obtenerConductor();
        }

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
