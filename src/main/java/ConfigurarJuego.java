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
        }
    }

    void validarEntrada() {
        var respuesta = sc.next();

        if (!respuesta.matches("[1-2]")) {
            iniciarJuego();
        }else {
            configuracionJuego(respuesta);
        }
    }
}
