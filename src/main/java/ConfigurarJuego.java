public class ConfigurarJuego {
    void iniciarJuego() {
        var respuesta = Utilidades.validarNoVacio(Constantes.MENU);
        configuracionJuego(respuesta);
    }

    void configuracionJuego(String respuesta) {
        switch (respuesta){
            case "1":
                break;
            case "2":
            case "3":
                new ConfiguracionPorConsola().ejecutarJuegoPorConsola(respuesta);
                break;
            default:
                iniciarJuego();
        }
    }
}
