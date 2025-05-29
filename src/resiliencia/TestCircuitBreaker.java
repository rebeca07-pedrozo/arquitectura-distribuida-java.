package resiliencia;

import java.util.Random;

public class TestCircuitBreaker {
    public static void main(String[] args) throws InterruptedException {
        CircuitBreaker breaker = new CircuitBreaker();
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            String resultado = breaker.ejecutar(
                () -> {
                    if (random.nextBoolean()) {
                        throw new RuntimeException("Simulación de error");
                    }
                    return " Operación exitosa";
                },
                () -> " Fallback activado"
            );
            System.out.println("Resultado: " + resultado);
            Thread.sleep(1000);
        }
    }
}
