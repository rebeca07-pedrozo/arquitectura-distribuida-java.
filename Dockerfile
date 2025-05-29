FROM openjdk:17
WORKDIR /app
COPY ./src /app/src
RUN javac -d /app/out src/**/**/*.java
CMD ["java", "-cp", "out", "resiliencia.RetryWithBackoff"]
