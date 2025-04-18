package com.atguigu.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 订单服务配置类
 * @Author: yiwenli
 * @Date: 2025/4/8
 */
@Configuration
public class OrderServiceConfig {

    // TODO 注解式负载均衡：这样RestTemplate自带负载均衡
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // TODO 日志全纪录组件
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    // TODO 重试器
    /*@Bean
    Retryer retryer() {
        return new Retryer.Default();
    }*/
}
