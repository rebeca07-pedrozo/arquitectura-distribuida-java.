package balanceador;

import java.util.concurrent.*;

public class RoundRobin {

    private final ExecutorService[] ejecutores;
    private int indice = 0;

    public RoundRobin(int cantidad) {
        ejecutores = new ExecutorService[cantidad];
        for (int i = 0; i < cantidad; i++) {
            ejecutores[i] = Executors.newSingleThreadExecutor();
        }
    }

    public void enviarTarea(Runnable tarea) {
        int actual = indice;
        ejecutores[actual].submit(() -> {
            System.out.println("Ejecutando tarea en hilo " + actual);
            tarea.run();
        });
        indice = (indice + 1) % ejecutores.length;
    }

    public void cerrar() {
        for (ExecutorService e : ejecutores) {
            e.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RoundRobin balanceador = new RoundRobin(3);

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            balanceador.enviarTarea(() -> System.out.println("Tarea " + finalI + " ejecutada"));
            Thread.sleep(500);
        }

        Thread.sleep(2000);
        balanceador.cerrar();
    }
}
