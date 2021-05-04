package utils;

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

    public static void cerrarScanner() {
        SC.close();
    }

    public static void iniciarCarrera(){
        System.out.println("\nPara empezar la carrera presione enter");
        SC.nextLine();
    }
}
