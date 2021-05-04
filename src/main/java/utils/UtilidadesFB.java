package utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import connection.ConexionFirebase;
import models.Carro;
import models.Conductor;
import models.Resultado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UtilidadesFB {
    private static List<QueryDocumentSnapshot> obtenerDocumentos(String collection) {
        ApiFuture<QuerySnapshot> future = ConexionFirebase.db.collection(collection).get();
        List<QueryDocumentSnapshot> documents = new ArrayList<>();

        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return documents;
    }

    public static List<Conductor> obtenerConductores(String collection) {
        List<Conductor> conductores = new ArrayList<>();
        var documents = obtenerDocumentos(collection);
        for (QueryDocumentSnapshot document : documents) {
            var carro = ((HashMap<?, ?>) document.getData().get("Carro"));
            conductores.add(
                    new Conductor(document.getId(),
                            document.getData().get("Nombre").toString(),
                            Integer.parseInt(document.getData().get("Victorias").toString()),
                            new Carro(carro.get("Color").toString(),
                                    carro.get("Marca").toString(),
                                    carro.get("Modelo").toString())));
        }

        return conductores;
    }

    public static List<Resultado> obtenerResultados(String collection) {
        List<Resultado> resultados = new ArrayList<>();
        var documents = obtenerDocumentos(collection);
        for (QueryDocumentSnapshot document : documents) {
            var conductoresGanadores = ((HashMap<?, ?>) document.getData().get("Ganadores"));
            List<String> idGanadores = new ArrayList<>();
            idGanadores.add(conductoresGanadores.get("Primero").toString());
            idGanadores.add(conductoresGanadores.get("Segundo").toString());
            idGanadores.add(conductoresGanadores.get("Tercero").toString());

            resultados.add(new Resultado(document.getId(),
                    document.getData().get("NombreCarrera").toString(),
                    Integer.parseInt(document.getData().get("Carriles").toString()),
                    Integer.parseInt(document.getData().get("DistanciaTotal").toString()),
                    obtenerListaConductores(idGanadores)));
        }

        return resultados;
    }

    private static List<Conductor> obtenerListaConductores(List<String> idGanadores) {
        List<Conductor> conductores = new ArrayList<>();
        idGanadores.forEach(ganador -> {
            DocumentReference docRef = ConexionFirebase.db.collection("conductores").document(ganador);

            ApiFuture<DocumentSnapshot> future = docRef.get();

            DocumentSnapshot document = null;
            try {
                document = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            if (document.exists()) {
                conductores.add(new Conductor(document.getId(),
                        document.getData().get("Nombre").toString(),
                        Integer.parseInt(document.getData().get("Victorias").toString())));
            } else {
                System.out.println("No such document!");
            }
        });

        return conductores;
    }
}
