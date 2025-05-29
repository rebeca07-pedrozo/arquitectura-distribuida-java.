# Simulador de Arquitecturas Distribuidas en Java

Este proyecto implementa una serie de simulaciones para conceptos de sistemas distribuidos usando Java de escritorio. Está diseñado para ejecutarse como aplicación de consola y cubre conceptos clave como resiliencia, balanceo de carga y failover.

# Dockerfile
FROM openjdk:17
WORKDIR /app
COPY ./src /app/src
RUN javac -d /app/out src/**/**/*.java
CMD ["java", "-cp", "out", "resiliencia.RetryWithBackoff"]
 ```
 docker build -t simulador-java .
docker run --rm simulador-java
```
Para ejecutar la imagen: *docker run --rm simulador-java*


## Objetivo

Desarrollar e implementar soluciones técnicas que simulen arquitecturas distribuidas aplicando mecanismos de resiliencia, balanceo de carga y monitoreo para mejorar la estabilidad y el control del sistema.

## Módulos implementados

### 1. Simulación de Servidores de Mensajes

- Tres instancias independientes de servidor escuchando en diferentes puertos.
- Cliente que envía mensajes manualmente a cada servidor.

### 2. Balanceo de Carga Round Robin

- Round Robin local implementado entre múltiples hilos que simulan procesos.
- Cada tarea es asignada de forma cíclica.

### 3. Reintentos con Backoff Exponencial

- Cliente que intenta conectarse a un servidor por socket.
- Si la conexión falla, reintenta con tiempos de espera exponenciales.

### 4. Circuit Breaker

- Si una función falla 3 veces consecutivas, el circuito se abre.
- Mientras el circuito esté abierto, se usa una función alternativa.
- Se restablece tras un tiempo de espera.

### 5. Failover entre Nodos Simulados

- Múltiples hilos simulan nodos de procesamiento.
- Si un nodo falla, las tareas se redirigen automáticamente al siguiente disponible.

## Estructura del proyecto

```
src/
├── balanceador/ # Round Robin
├── cliente/ # Cliente de sockets
├── resiliencia/ # Circuit Breaker y Backoff
├── servidores/ # Servidores de sockets
├── failover/ # Simulación de nodos y failover
```

## Requisitos

- JDK 11 o superior
- Gradle (opcional, pero recomendado)
- GitHub Codespaces o entorno Java local

## Compilación y ejecución

Desde el directorio raíz del proyecto:

```bash
javac -d out src/**/**/*.java
java -cp out resiliencia.RetryWithBackoff
