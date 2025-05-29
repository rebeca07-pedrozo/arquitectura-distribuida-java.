package resiliencia;

import java.util.function.Supplier;

public class CircuitBreaker {

    private enum Estado {CERRADO, ABIERTO, SEMI_ABIERTO}

    private Estado estado = Estado.CERRADO;
    private int contadorErrores = 0;
    private final int maxErrores = 3;
    private long tiempoApertura = 0;
    private final long timeout = 5000; // milisegundos

    public <T> T ejecutar(Supplier<T> funcion, Supplier<T> fallback) {
        long ahora = System.currentTimeMillis();

        if (estado == Estado.ABIERTO) {
            if (ahora - tiempoApertura > timeout) {
                estado = Estado.SEMI_ABIERTO;
                System.out.println("⏳ Circuito en prueba (semi-abierto)...");
            } else {
                System.out.println("🚫 Circuito abierto. Ejecutando fallback.");
                return fallback.get();
            }
        }

        try {
            T resultado = funcion.get();
            resetear();
            return resultado;
        } catch (Exception e) {
            manejarFallo();
            return fallback.get();
        }
    }

    private void manejarFallo() {
        contadorErrores++;
        System.out.println("❌ Fallo detectado. Total errores: " + contadorErrores);

        if (contadorErrores >= maxErrores) {
            estado = Estado.ABIERTO;
            tiempoApertura = System.currentTimeMillis();
            System.out.println("🛑 Circuito abierto por fallos consecutivos.");
        }
    }

    private void resetear() {
        if (estado != Estado.CERRADO) {
            System.out.println("✅ Circuito cerrado. Todo estable.");
        }
        estado = Estado.CERRADO;
        contadorErrores = 0;
    }
}
