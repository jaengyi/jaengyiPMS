package com.example.apigateway.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * A custom Gateway Filter that validates the JWT token present in the Authorization header.
 * This filter is applied globally to routes that require authentication.
 */
@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    /**
     * Configuration class for the filter. Can be used to pass parameters from application.yml.
     */
    public static class Config {
        // Put configuration properties here if needed
    }

    /**
     * The core logic of the filter. It checks for the Authorization header and validates the JWT.
     * @param config The configuration object for this filter.
     * @return The GatewayFilter instance.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 1. Check if the Authorization header is present
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            // 2. Retrieve the token from the header
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer ", "");

            // 3. Validate the JWT token
            if (!isJwtValid(jwt)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }

            // 4. If valid, proceed with the filter chain
            return chain.filter(exchange);
        };
    }

    /**
     * Parses and validates the JWT token.
     * @param jwt The JWT token string.
     * @return True if the token is valid, false otherwise.
     */
    private boolean isJwtValid(String jwt) {
        boolean returnValue = true;
        String subject = null;

        try {
            // Parse the token using the secret key
            subject = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        } catch (Exception ex) {
            log.error("JWT parsing failed: {}", ex.getMessage());
            returnValue = false;
        }

        // Check if the subject (e.g., userId) is null or empty
        if (subject == null || subject.isEmpty()) {
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Handles errors by setting the appropriate HTTP status and logging the error message.
     * @param exchange The current server web exchange.
     * @param err The error message.
     * @param httpStatus The HTTP status to return.
     * @return A Mono<Void> to indicate the response is complete.
     */
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }
}
