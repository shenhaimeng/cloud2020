package com.shm.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author shenhaimeng
 * @date 2020/4/9 - 23:25
 */
@Component
public class MyLogGateWayFilter implements GlobalFilter,Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if(name == null)
        {
            System.out.println("简要鉴权");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        // 通过后将走下一个过滤器
        return chain.filter(exchange);
    }

    // 加载过滤器的顺序
    @Override
    public int getOrder() {
        return 0;
    }
}
