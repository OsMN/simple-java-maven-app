FROM bitnami/java
WORKDIR /
ADD app.jar app.jar
EXPOSE 8082
CMD java -jar app.jar