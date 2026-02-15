package com.medical.appointment.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
public class TokenFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String URI = exchange.getRequest().getURI().getPath();

        if(URI.contains("login") || URI.contains("actuator") || URI.contains("register")){

            return chain.filter(exchange);
        }

        return exchange.getPrincipal().flatMap(principal -> {

            if(principal instanceof JwtAuthenticationToken jwtAuth){

                String userName = jwtAuth.getName();
                String roles = jwtAuth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));

                ServerHttpRequest mutated = exchange.getRequest().mutate()
                        .header("X-User-Name",userName)
                        .header("X-User-Roles",roles)
                        .build();

                return chain.filter(exchange.mutate().request(mutated).build());
            }

            return chain.filter(exchange);
        });
    }
}
