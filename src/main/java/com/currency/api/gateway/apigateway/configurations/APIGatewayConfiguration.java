package com.currency.api.gateway.apigateway.configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes().
                route(p->p.path("/get-route").
                        filters(f->f.addRequestHeader("RouteRequestHeader","value1").addRequestParameter("RouteRequestParam","Value2"))
                        .uri("http://httpbin.org:80/")).//adding request header param on getting /get request pattern
                route(p->p.path("/currency-exchange/**").uri("lb://currency-exchange")). //mapping url contains "currency-exchange" with LB
                route(p->p.path("/currency-conversion/**").uri("lb://currency-conversion")). //mapping url contains "currency-conversion" with LB
                route(p->p.path("/currency-conversion/**").filters(f -> f.rewritePath("/currency-conversion-route/","/currency-conversion/")).uri("lb://currency-conversion"))
                .build();
    }
}
