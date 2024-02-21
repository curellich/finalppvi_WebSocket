FROM openjdk:20-slim-buster
ADD /build/libs/finalppviwebsocket-0.0.1-SNAPSHOT.jar app.jar
ENV spring.data.redis.host=localhost
ENV spring.data.redis.port=6379
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]