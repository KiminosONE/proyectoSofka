import java.util.Scanner;

public class ConfigurarJuego {
    Scanner sc = new Scanner(System.in);

    void iniciarJuego() {
        System.out.println(Constantes.MENU);
        validarEntrada();
    }

    void configuracionJuego(String respuesta) {
        switch (respuesta){
            case "1":
                break;
            case "2":
                new ConfiguracionPorConsola().ejecutarJuegoPorConsola();
                break;
            case "3":
                new ConfiguracionPorFB().ejecutarJuegoPorFB();
                break;
        }
    }

    void validarEntrada() {
        var respuesta = sc.next();

        if (!respuesta.matches("[1-3]")) {
            iniciarJuego();
        }else {
            configuracionJuego(respuesta);
        }
    }
}
