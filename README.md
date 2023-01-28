# quotation-management

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=leonardoscalabrini_quotation-management&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=leonardoscalabrini_quotation-management)

## Dev quick start ##

1. Install dependencies
````
mvn clean install
````

2. Install Infraestructure
````
cd docker
docker-compose up
````

3. Start locally
````
mvn spring-boot:run
````

## Docker quick start ##

1. To build image
````
docker build --tag=quotation-management:latest .
````

2. To run container locally
````
docker run --name quotation-management -p 8081:8080 -e SPRING_PROFILE=dockerdev --network=inatel quotation-management
````
