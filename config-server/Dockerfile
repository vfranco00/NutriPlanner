FROM openjdk:24-jdk-slim AS builder

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:24-jdk-slim AS prod

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8888

CMD ["java", "-jar", "app.jar"]