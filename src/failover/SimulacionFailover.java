package failover;

import java.util.ArrayList;
import java.util.List;

public class SimulacionFailover {

    public static void main(String[] args) throws InterruptedException {
        List<NodoSimulado> nodos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            NodoSimulado nodo = new NodoSimulado(i);
            nodos.add(nodo);
            new Thread(nodo).start();
        }

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Nodo 1 será desactivado.");
                nodos.get(1).desactivar();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        for (int i = 0; i < 6; i++) {
            String tarea = "Tarea " + i;
            asignarTareaConFailover(nodos, tarea);
            Thread.sleep(1000);
        }
    }

    public static void asignarTareaConFailover(List<NodoSimulado> nodos, String tarea) {
        for (NodoSimulado nodo : nodos) {
            if (nodo.estaActivo()) {
                try {
                    nodo.ejecutarTarea(tarea);
                    return;
                } catch (Exception e) {
                    System.out.println("Nodo " + nodo.getId() + " falló.");
                }
            }
        }
        System.out.println("No hay nodos activos disponibles para la tarea: " + tarea);
    }
}
