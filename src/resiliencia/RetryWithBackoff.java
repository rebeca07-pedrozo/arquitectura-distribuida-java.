package resiliencia;

import java.io.IOException;
import java.net.Socket;

public class RetryWithBackoff {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5050;
        int maxIntentos = 5;

        for (int intento = 1; intento <= maxIntentos; intento++) {
            try {
                System.out.println("Intentando conectar... intento " + intento);
                Socket socket = new Socket(host, puerto);
                System.out.println("Conectado exitosamente en el intento " + intento);
                socket.close();
                break;
            } catch (IOException e) {
                int espera = (int) Math.pow(2, intento);
                System.out.println("Falló la conexión. Reintentando en " + espera + " segundos...");
                try {
                    Thread.sleep(espera * 1000L);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
