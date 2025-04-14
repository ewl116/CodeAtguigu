package com.atguigu.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: 响应时间全局过滤
 * @Author: yiwenli
 * @Date: 2025/4/14
 */
@Component
@Slf4j
public class ResponseTimeGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String uri = request.getURI().toString();
        long start = System.currentTimeMillis();
        log.info("请求【{}】开始，时间：{}", uri, start);
        // 放行。注意chain.filter(exchange);是异步的，执行后不会等执行结束就会继续执行下面的代码
        Mono<Void> filter = chain.filter(exchange).doFinally((result) -> {
            long end = System.currentTimeMillis();
            log.info("请求【{}】结束，时间：{}，耗时：{}ms", uri, end, end - start);
        });

        return filter;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
