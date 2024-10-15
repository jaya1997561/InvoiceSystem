FROM openjdk:17
EXPOSE 8080
ADD target/invoice-system.jar invoice-system.jar
CMD ["java", "-jar", "/invoice-system.jar"]