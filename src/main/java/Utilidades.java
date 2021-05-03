import java.util.Scanner;

public class Utilidades {
    private static final Scanner sc = new Scanner(System.in);
    static  String validarNoVacio(String mensaje) {
        var valor = "";
        do {
            System.out.println(mensaje);
            valor = sc.nextLine();

        } while (valor.isEmpty());

        return valor;
    }

    static int validarNumerico(String mensaje) {
        var valor = "";
        do {
            System.out.println(mensaje);
            valor = sc.nextLine();
        } while (!valor.matches("[0-9]+"));

        return Integer.parseInt(valor);
    }
}
