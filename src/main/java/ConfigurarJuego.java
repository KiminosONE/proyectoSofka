import utils.UtilidadesValidacion;

public class ConfigurarJuego {
    void iniciarJuego() {
        var respuesta = UtilidadesValidacion.validarNoVacio(Constantes.MENU);
        configuracionJuego(respuesta);
    }

    void configuracionJuego(String respuesta) {
        switch (respuesta) {
            case "1":
                new ConfiguracionPorInstancias().ejecutarJuegoPorInstancias();
                break;
            case "2":
            case "3":
                new ConfiguracionPorConsola().ejecutarJuegoPorConsola(respuesta);
                break;
            case "4":
                new ObtenerResultados().listarResultados();
                break;
            case "5":
                System.exit(0);
            default:
                iniciarJuego();
        }
    }
}
