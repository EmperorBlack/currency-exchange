FROM adoptopenjdk/openjdk11:ubi
EXPOSE 9090
ADD target/currency-exchange.jar currency-exchange.jar
ENTRYPOINT ["java", "-jar", "currency-exchange.jar"]