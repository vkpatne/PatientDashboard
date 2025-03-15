FROM openjdk:17
COPY target/prd.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
