package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  RouteLocator gateway(RouteLocatorBuilder rlb) {
    return rlb.routes()
        .route(
            rs -> rs.path("/proxy")
                .and()
                .host("*.spring.io")
                .filters(fs -> fs.setPath("/customers"))
                .uri("http://localhost:8080/"))
        .build();
  }

}
