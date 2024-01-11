FROM openjdk:17 as builder
WORKDIR backend
ADD target/*.jar app.jar
RUN java  -Djarmode=layertools -jar app.jar extract

FROM openjdk:17
WORKDIR backend-app
COPY --from=builder backend/dependencies/ ./
COPY --from=builder backend/spring-boot-loader/ ./
COPY --from=builder backend/snapshot-dependencies/ ./
COPY --from=builder backend/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]