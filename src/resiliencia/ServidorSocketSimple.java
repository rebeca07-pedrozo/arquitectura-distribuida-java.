package resiliencia;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorSocketSimple {
    public static void main(String[] args) {
        int puerto = 5050;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("🚀 Servidor escuchando en puerto " + puerto);
            serverSocket.accept(); // Acepta una conexión y se cierra
            System.out.println("✅ Conexión recibida.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
