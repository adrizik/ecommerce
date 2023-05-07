FROM openjdk:8
EXPOSE 8080
ADD target/ecommerce-integration.jar ecommerce-integration.jar
ENTRYPOINT ["java","-jar","/ecommerce-integration.jar"]