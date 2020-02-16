FROM gradle:3.4-jdk8 as builder
WORKDIR /home/gradle/project
USER root
COPY ./ ./
RUN chown -R gradle:gradle .
USER gradle 
RUN gradle shadowJar
RUN ls build/libs


FROM adoptopenjdk/openjdk8
WORKDIR /app
COPY --from=builder /home/gradle/project/build/libs/ESARack-1.0-all.jar ./
ENTRYPOINT ["java", "-jar", "ESARack-1.0-all.jar"]