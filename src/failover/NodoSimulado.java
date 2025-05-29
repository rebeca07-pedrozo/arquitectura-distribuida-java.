package failover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NodoSimulado implements Runnable {
    private final int id;
    private volatile boolean activo = true;

    public NodoSimulado(int id) {
        this.id = id;
    }

    public void desactivar() {
        this.activo = false;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void ejecutarTarea(String tarea) {
        if (!activo) {
            throw new IllegalStateException("Nodo " + id + " está inactivo.");
        }
        System.out.println(" Nodo " + id + " ejecutando tarea: " + tarea);
    }

    @Override
    public void run() {
        while (activo) {
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(" Nodo " + id + " fue desactivado.");
    }

    public int getId() {
        return id;
    }
}
