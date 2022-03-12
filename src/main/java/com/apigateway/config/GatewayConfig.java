package com.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Autowired
    private AdminAuthenticationFilter adminFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()

                // user
                .route("USER-SERVICE", r -> r.path("/api/user-service/auth/**")
                        .uri("lb://USER-SERVICE/auth"))
                .route("USER-SERVICE", r -> r.path("/api/user-service/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://USER-SERVICE/user"))
                .route("USER-SERVICE", r -> r.path("/api/user-service/admin/**")
                        .filters(f -> f.filter(adminFilter))
                        .uri("lb://USER-SERVICE/admin"))

                // product
                .route("PRODUCT-SERVICE", r -> r.path("/api/product-service/admin/**")
                        .filters(f -> f.filter(adminFilter))
                        .uri("lb://PRODUCT-SERVICE/admin"))
                .route("PRODUCT-SERVICE", r -> r.path("/api/product-service/product/**")
                        .uri("lb://PRODUCT-SERVICE/product"))
                .route("PRODUCT-SERVICE", r -> r.path("/api/product-service/image/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://PRODUCT-SERVICE/image"))
                .route("PRODUCT-SERVICE", r -> r.path("/api/product-service/attribute/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://PRODUCT-SERVICE/attribute"))

                // order
                .route("ORDER-SERVICE", r -> r.path("/api/order-service/admin/**")
                        .filters(f -> f.filter(adminFilter))
                        .uri("lb://ORDER-SERVICE/admin"))
                .route("ORDER-SERVICE", r -> r.path("/api/order-service/order/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://order-service/order"))
                .route("ORDER-SERVICE", r -> r.path("/api/order-service/order-detail/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://order-service/order-detail"))

                // cart
                .route("CART-SERVICE", r -> r.path("/api/cart-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://CART-SERVICE"))

                // category
                .route("CATEGORY-SERVICE", r -> r.path("/api/category-service/admin/**")
                        .filters(f -> f.filter(adminFilter))
                        .uri("lb://CATEGORY-SERVICE/admin"))
                .route("CATEGORY-SERVICE", r -> r.path("/api/category-service/category/**")
                        .uri("lb://CATEGORY-SERVICE/category"))

                // comment
                .route("COMMENT-SERVICE", r -> r.path("/api/comment-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://COMMENT-SERVICE"))

                // supplier
                .route("SUPPLIER-SERVICE", r -> r.path("/api/supplier-service/admin/**")
                        .filters(f -> f.filter(adminFilter))
                        .uri("lb://SUPPLIER-SERVICE/admin"))
                .route("SUPPLIER-SERVICE", r -> r.path("/api/supplier-service/supplier/**")
                        .uri("lb://SUPPLIER-SERVICE/supplier"))
                .build();
    }

}
