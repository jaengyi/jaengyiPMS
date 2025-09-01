package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the API Gateway service.
 * This service acts as a reverse proxy, routing requests from clients to the appropriate microservices.
 */
@SpringBootApplication
public class ApiGatewayApplication {

    /**
     * The main method that starts the Spring Boot application.
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
