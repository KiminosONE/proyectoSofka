import connection.ConexionFirebase;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConexionFirebase.conectar();
        new ConfigurarJuego().iniciarJuego();
    }
}
