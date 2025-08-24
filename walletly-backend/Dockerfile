FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY pom.xml .

COPY .mvn/ .mvn/
COPY mvnw .
RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src/ src/

RUN ./mvnw package -DskipTests

CMD ["./mvnw", "spring-boot:run"]

EXPOSE 8080