FROM openjdk:18
FROM maven:3.8.7-openjdk-18

WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

COPY . /app
RUN mvn -v
RUN mvn clean install -DskipTests

CMD ["mvn", "spring-boot:run"]