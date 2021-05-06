package utils;

import models.Juego;
import models.Pista;

import java.util.Scanner;

public class UtilidadesValidacion {
    private static final Scanner SC = new Scanner(System.in);

    public static String validarNoVacio(String mensaje) {
        var valor = "";
        do {
            System.out.println(mensaje);
            valor = SC.nextLine();

        } while (valor.trim().isEmpty());

        return valor;
    }

    public static int validarNumerico(String mensaje) {
        var valor = "";
        do {
            System.out.println(mensaje);
            valor = SC.nextLine();
        } while (!valor.trim().matches("[0-9]+"));

        return Integer.parseInt(valor);
    }

    public static void presionarParaContinuar() {
        System.out.println("\nPresione enter para continuar");
        SC.nextLine();
    }

    public static void datosCarrera(Juego juego) {
        System.out.println("\n" + juego.getNombreCarrera() +
                "\nDistancia total: " + juego.getPista().getDistanciaTotal() +
                " || Carriles: " + juego.getPista().getCarriles().size());
    }

    public static void vistaListaJugadores(Pista pista) {
        pista.getCarriles().forEach(carril -> System.out.println(carril.getConductor().getNombre()
                + " maneja un " + carril.getConductor().getCarro().getMarca()
                + " y a ganado: " + carril.getConductor().getVictorias()));
    }
}
