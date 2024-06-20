FROM maven:3.9 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar

ENV PGHOST=db
ENV PGDATABASE=mydatabase
ENV PGUSER=myuser
ENV PGPASSWORD=mypassword
ENV PGPORT=5432

CMD ["java", "-jar", "app.jar"]