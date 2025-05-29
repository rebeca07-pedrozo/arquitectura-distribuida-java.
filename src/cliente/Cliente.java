package cliente;

import servidor.ServidorMensaje;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws InterruptedException {
        // Iniciar tres servidores en hilos
        new Thread(new ServidorMensaje(5001)).start();
        new Thread(new ServidorMensaje(5002)).start();
        new Thread(new ServidorMensaje(5003)).start();

        Thread.sleep(2000);

        int[] puertos = {5001, 5002, 5003};
        for (int i = 0; i < 6; i++) {
            int puerto = puertos[i % puertos.length];
            enviarMensaje("Mensaje " + (i + 1), "localhost", puerto);
            Thread.sleep(1000);
        }
    }

    public static void enviarMensaje(String mensaje, String host, int puerto) {
        try (Socket socket = new Socket(host, puerto)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
