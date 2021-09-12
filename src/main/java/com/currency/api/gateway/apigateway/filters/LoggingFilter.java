package com.currency.api.gateway.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
/*
* GlobalFilter : Filter to logging every request comes via API Gateway
* spring clod gateway is simple way to route to API, defind predicate, filters , Path rewriting
* simple in cross cutting concerns like security Monitoring Metrix etx
* Built on spring webflux
* integrate with spring cloud discovery client (LB)
* */

@Component
public class LoggingFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("requested path uri - "+exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
