package com.atguigu.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description: 请求头中添加XToken字段
 * @Author: yiwenli
 * @Date: 2025/4/9
 */
@Component
public class XTokenRequestInterceptor implements RequestInterceptor {

    /**
     * @description 请求拦截器
     * @param
     * @return
     * @author yiwenli
     * @date 2025/4/9
     */
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("XTokenRequestInterceptor......");
        template.header("X-Token", UUID.randomUUID().toString());
    }
}
