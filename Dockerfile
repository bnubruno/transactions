FROM adoptopenjdk/openjdk11:ubi
RUN mkdir -p /app/
COPY build/libs/*.jar /app/app.jar
COPY scripts/entrypoint.sh /app
ENTRYPOINT ["sh", "/app/entrypoint.sh"]