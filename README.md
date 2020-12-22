# myRetail Product API

This service is for serving product information for myRetail product API

### Technology Stack
Java 11, Spring boot, Cassandra, Gradle, Docker, Jacoco, Checkstyle, Swagger2

### Getting Started
Follow the instructions to run the application on local machine with Cassandra on Docker

### Install docker:

```
For mac : https://docs.docker.com/docker-for-mac/install/
For Windows: https://docs.docker.com/docker-for-windows/install/
```

### Install Gradle:
```
https://gradle.org/install/
```

### Install Openjdk11:
```
https://openjdk.java.net/install/
```

### Clone repository 

```
git clone https://github.com/dmsdayanithi/myRetail-product-api.git
```

### Run Cassandra Docker Instance 

```
cd my-retail-product-api
cd docker
docker-compose build
docker-compose up
```


### Build and Run:
Switch to application(repo) root directory
```
gradlew clean build
```

### Sample Requests
>For more api documentation please refer swagger
>http://localhost:8080/swagger-ui.html#/

#### Product Price Update
```
curl --location --request PUT 'http://localhost:8080/v1/products/13860420' \
--header 'Content-Type: application/json' \
--header 'Content-Type: text/plain' \
--data-raw '{
    "id": 13860420,
    "current_price": {
        "value": 14.49,
        "currency_code": "USD"
    }
}'
```

#### Get Product Details
```
curl --location --request GET 'http://localhost:8080/v1/products/13860420' \
--header 'Content-Type: application/json'
```

#### Application Health Check
>http://localhost:8080/actuator/health

### Reference Documentation
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.1/gradle-plugin/reference/html/)
* [Official Gradle documentation](https://docs.gradle.org)
* [Cassandra](http://cassandra.apache.org/doc/latest/architecture/index.html)
* [Checkstyle](https://checkstyle.sourceforge.io/)
* [Jacoco](https://www.jacoco.org/jacoco/trunk/index.html)

#### Author: Dayanithi Devarajan
        
   ***email: dmsdayanithi@gmail.com***