[Master Microservices with Spring Boot and Spring Cloud](https://www.udemy.com/microservices-with-spring-boot-and-spring-cloud/)

#### Ports:

| **Application** | **Port** |
| --- | --- |
| `Spring Cloud Config Server` | 8888 |
| | | |
| | | |
| `Limits service` | 8080, 8081, ... |
| `Currency Exchange Service` | 8000, 8001, 8002, ... |
| `Currency Conversion(Calculation) Service` | 8100, 8101, 8102, ... |
| | | |
| | | |
| `Netflix Zuul API Gateway Server` | 8765 |
| `Netflix Eureka Naming Server` | 8761 |
| `Zipkin Distributed Tracing Server` | 9411 |

#### Notes:
1. **`Spring Cloud Sleuth`** - it adds **unique id** to a request so it's possible to track such request in a distributed system.
    1. This feature will be implemented in the following microservices:
        1. `currency-conversion-service`
        2. `currency-exchange-service`
        3. `netflix-zuul-api-gateway-server`
2. **`Spring Cloud Zipkin`** - tool (distributed tracing server) used to track already `marked` requests by `Spring Cloud Sleuth`
3. **Section 4, Lecture 101 (Understanding the need for Spring Cloud Bus)**
    1. Once I have `SpringCloudConfigServer`, `LimitsService(8080)` and `LimitsService(8081)` running, I need to change some configuration
    for `LimitsService` microservice editing configuration file inside `git` repository (in my case `limits-service-dev.yml`)
        1. First I need to change a property I'm interested
        2. Then I need to commit that change in git
            1. **Unfortunately** this change is not reflected as I have to restart `SpringCloudConfigServer`, `LimitsService(8080)` and `LimitsService(8081)`
            2. To avoid this I can:
                1. Send a `POST` request inside `Postman` to: **`http://localhost:8080(8081)/actuator/refresh`** depending how many instances are running
                2. Unfortunately, this `would get a Resource Not Found Error`
                3. I need to `enable all Actuator URL(s)` -> `management.endpoints.web.exposure.include=*` inside `limits-service.yml`
                4. After that, once I send a `POST` request to above URL, I get:
                ```json
                [
                 "config.client.version",
                 "limits-service.minimum"
                ]
                ```
                and it's successful response.
            3. It's gonna be quiet painful in case if I had e.g. `100 instances` of `LimitsService` microservice as 
            I would have to make `POST` request 100 times...
            4. There is better approach using `Spring Cloud Bus`.
4. **Section 4, Lecture 102 (Implementing Spring Cloud Bus)** 
    1. In this case to refresh all instances with new configuration property(ies), I have to make a `POST` request 
    to <u>**only one of them**</u> and rest will be updated accordingly:
        * `http://localhost:8080/actuator/bus-refresh`