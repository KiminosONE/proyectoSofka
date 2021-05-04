import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.WriteResult;
import connection.ConexionFirebase;
import models.Conductor;
import models.Juego;
import models.Podio;
import utils.UtilidadesValidacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class IniciarCarrera {
    Podio podio = new Podio();
    Map<String, Integer> listaPosicion = new HashMap<>();
    Juego configuracion;

    void empezarCarrera(Juego configuracionjuego, List<Conductor> conductores) {
        podio.setConductores(new ArrayList<>());
        obtenerListaDeCarros(conductores);
        configuracion = configuracionjuego;
        temporizador();
        obtenerGanadores(conductores, podio.getConductores());
        guardandoDatosCarrera();
    }

    private void guardandoDatosCarrera() {
        actualizarConductoresFB();
        subirResultados();
        imprimirGanadores();
    }

    private void temporizador() {
        System.out.println("La carrera iniciara en:");
        for (int i = 3; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Constantes.GO);
    }

    private void imprimirGanadores() {
        System.out.println("Los ganadores de la carrera " + configuracion.getNombreCarrera() + " son:");
        podio.getConductores().forEach(conductor -> {
            var index = podio.getConductores().indexOf(conductor) + 1;
            System.out.println(index + "). " + conductor.getNombre() + " a ganado: " + conductor.getVictorias());
        });
        UtilidadesValidacion.presionarParaContinuar();
        new ConfigurarJuego().iniciarJuego();
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

    private void actualizarConductoresFB() {
        podio.getConductores().forEach(conductor -> {
            DocumentReference docRef = ConexionFirebase.db.collection("conductores").document(conductor.getId());

            ApiFuture<WriteResult> future = docRef.update("Victorias", FieldValue.increment(1));
            var contVictorias = conductor.getVictorias();
            conductor.setVictorias(contVictorias + 1);

            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private void subirResultados() {
        ApiFuture<DocumentReference> addedDocRef = ConexionFirebase.db.collection("resultados").add(datosResultado());
        try {
            System.out.println("Resultados de la carrera subidos: " + addedDocRef.get().getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> datosResultado() {
        return Map.of("NombreCarrera", configuracion.getNombreCarrera(),
                "Carriles", configuracion.getPista().getCarriles().size(),
                "DistanciaTotal", configuracion.getPista().getDistanciaTotal(),
                "Ganadores", listaGanadores());
    }

    private Map<String, Object> listaGanadores() {
        return Map.of("Primero", podio.getConductores().get(0).getId(),
                "Segundo", podio.getConductores().get(1).getId(),
                "Tercero", podio.getConductores().get(2).getId());
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