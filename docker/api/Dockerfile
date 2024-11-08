# WIP

FROM openjdk-23
 
WORKDIR /app
 
COPY ../../back/.mvn/ .mvn
COPY ../../back/mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
COPY ../../back/src ./src
 
CMD ["./mvnw", "spring-boot:run"]