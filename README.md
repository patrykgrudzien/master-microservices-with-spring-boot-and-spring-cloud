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