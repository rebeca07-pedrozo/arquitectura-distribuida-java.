package servidor;

import java.io.*;
import java.net.*;

public class ServidorMensaje implements Runnable {
    private final int puerto;

    public ServidorMensaje(int puerto) {
        this.puerto = puerto;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en puerto " + puerto);
            while (true) {
                Socket cliente = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String mensaje = in.readLine();
                System.out.println("Servidor " + puerto + " recibió: " + mensaje);
                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
