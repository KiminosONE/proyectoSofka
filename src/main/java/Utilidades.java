import java.util.Scanner;

public class Utilidades {
    private static final Scanner SC = new Scanner(System.in);

    static  String validarNoVacio(String mensaje) {
        var valor = "";
        do {
            System.out.println(mensaje);
            valor = SC.nextLine();

        } while (valor.trim().isEmpty());

        return valor;
    }

    static int validarNumerico(String mensaje) {
        var valor = "";
        do {
            System.out.println(mensaje);
            valor = SC.nextLine();
        } while (!valor.trim().matches("[0-9]+"));

        return Integer.parseInt(valor);
    }

    static void cerrarScanner(){
        SC.close();
    }
}
