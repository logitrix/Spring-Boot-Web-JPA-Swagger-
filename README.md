# Dann Restaurant & Tourist Record Service 

Development: Saturday, January 13, 2023 - 9pm

Spring Boot + JPA + Spring Web + Swagger

## Tools
Java 19.0.1

Apache Maven 3.8.7

## Library

spring-boot-starter-parent 2.2.8.RELEASE

org.postgresql

spring-boot-starter-web

spring-boot-starter-data-jpa

springdoc-openapi-ui 1.2.32



## Usage

```java
# After cloning the repo run & build using maven
'mvn clean install'

# When successfully build
'mvn spring-boot:run'

# Access in the browser 'http://localhost:8080/swagger-ui/index.html'
# Use API Doc Path '/dann-restaurant-openapi'

# Example use endpoint 'http://localhost:8080/dann-resto/order' (POST)
# name could be email address / email addresses of the tourist/s who ordered
# id can be referred from endpoint 'http://localhost:8080/dann-resto/get-products' (GET) 
{
    "name":"Pedro 123",
    "orders": [
        {"id": 1},{"id": 2},{"id": 3}
    ]
}



