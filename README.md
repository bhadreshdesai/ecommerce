[![Build](https://github.com/bhadreshdesai/ecommerce/actions/workflows/gradle.yml/badge.svg)](https://github.com/bhadreshdesai/ecommerce/actions/workflows/gradle.yml)

## Setup
```shell
# Clone repository
git clone https://github.com/bhadreshdesai/ecommerce.git

# Start mysql and mongodb docker
cd ecommerce/docker
docker-compose up -d

# Build the project
cd ..
./gradlew clean build

# Run the project
./gradlew bootRun
```

[phpMyadmin](http://localhost:8001/)

[mongo-express](http://localhost:8002/)

[actuator](http://localhost:8080/actuator)

[mappings](http://localhost:8080/actuator/mappings)

[swagger](http://localhost:8080/swagger-ui.html)

[api-docs](http://localhost:8080/v3/api-docs)


## ToDo
- [ ] Write integration test for product server [Youtube Int Test](https://www.youtube.com/watch?v=lh1oQHXVSc0&t=1404s)
- [ ] [Cucumber & Testcontainer: a BDD perfect match](https://medium.com/javarevisited/cucumber-testcontainer-a-bdd-perfect-match-956cf62cdf47) - [Github](https://github.com/fpaparoni/bddfun/blob/main/project/src/test/java/com/javastaff/bddfun/test/glue/SpringBootTestLoader.java)

## References

### Microservices
[Spring Boot Microservices Project Example - Part 1 | Building Services](https://www.youtube.com/watch?v=lh1oQHXVSc0)
[spring-boot-microservices-new github](https://github.com/SaiUpadhyayula/spring-boot-microservices-new)

### Cucumber
#### Springboot 2.6.4, Cucumber 7.2.3, junit5

[Selenium Spring Boot Cucumber Junit 5 Test Automation Project](https://www.swtestacademy.com/selenium-spring-boot-cucumber-junit5-project/)

[Selenium Spring Boot Cucumber Junit 5 Test Automation Project - github](https://github.com/swtestacademy/selenium-springboot/tree/junit-springboot-selenium)

#### Cucumber with JUnit 5 - state, step, result example
[Cucumber with JUnit 5](https://blog.cronn.de/en/testing/2020/08/17/cucumber-junit5.html)

[Cucumber with JUnit 5 - github](https://github.com/cronn/cucumber-junit5-example)

#### REST API Testing With Cucumber Using BDD Approach
[REST API Testing With Cucumber Using BDD Approach](https://www.softwaretestinghelp.com/rest-api-testing-with-bdd-cucumber/)

### RestAssured
[Integration Testing of SpringBoot using RestAssured](https://qaautomation.expert/2021/07/26/integration-testing-of-springboot-using-restassured/)
