FROM adoptopenjdk:11-jre-hotspot
VOLUME /tmp
ADD rentopia-model-core-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 9000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]