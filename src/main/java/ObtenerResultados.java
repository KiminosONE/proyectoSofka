import models.Resultado;
import utils.UtilidadesFB;
import utils.UtilidadesValidacion;

import java.util.ArrayList;
import java.util.List;

public class ObtenerResultados {
    List<Resultado> resultados = new ArrayList<>();

    void listarResultados() {
        resultados = UtilidadesFB.obtenerResultados("resultados");
        imprimirResultados();
        UtilidadesValidacion.presionarParaContinuar();
        new ConfigurarJuego().iniciarJuego();
    }

    private void imprimirResultados() {
        for (Resultado resultado : resultados) {
            vistaResultado(resultado);
        }
    }

    private void vistaResultado(Resultado resultado) {
        System.out.println("\n" + resultado.getNombreCarrera() +
                "\nDistancia total: " + resultado.getDistanciaTotal() +
                " || Carriles: " + resultado.getNumeroCarriles());
        vistaGanadores(resultado);
    }

    private void vistaGanadores(Resultado resultado) {
        resultado.getConductores().forEach(conductor -> {
            var index = resultado.getConductores().indexOf(conductor) + 1;
            System.out.println(index + "). " + conductor.getNombre() + " a ganado: " + conductor.getVictorias());
        });
    }
}
