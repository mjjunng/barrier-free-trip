FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/barrier-free-trip-1.0.jar Triply.jar
ENTRYPOINT ["java", "-jar", "Triply.jar"]