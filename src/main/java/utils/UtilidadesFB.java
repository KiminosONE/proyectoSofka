package utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import connection.ConexionFirebase;
import models.Carro;
import models.Conductor;
import models.Podio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            conductores.add(new Conductor(document.getId(), document.getData().get("Nombre").toString(),
                    new Carro(carro.get("Color").toString(), carro.get("Marca").toString(), carro.get("Modelo").toString())));
        }

        return conductores;
    }/*

    public static List<Podio> obtenerColeccion2(String collection) {
        ApiFuture<QuerySnapshot> future = ConexionFirebase.db.collection(collection).get();
        List<QueryDocumentSnapshot> documents = new ArrayList<>();
        List<Map<String, Object>> data = new ArrayList<>();

        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.getData());
            data.add(Map.of("id", document.getId(), "data", document.getData()));
        }

        return data;
    }*/
}
