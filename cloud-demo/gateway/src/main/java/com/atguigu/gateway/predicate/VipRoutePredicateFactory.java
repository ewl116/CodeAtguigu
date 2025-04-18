package com.atguigu.gateway.predicate;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Description: Vip路由断言工厂
 * @Author: yiwenli
 * @Date: 2025/4/14
 */
@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config> {

    public VipRoutePredicateFactory() {
        super(Config.class);
    }

    /**
     * @description 规则判断逻辑
     * @author yiwenli
     * @date 2025/4/14
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // localhost/search?q=haha&user=yiwenli
                ServerHttpRequest request = serverWebExchange.getRequest();
                String first = request.getQueryParams().getFirst(config.param);
                return StringUtils.hasText(first) && first.equals(config.value);
            }
        };
    }

    /**
     * @description 短写法时的参数顺序
     * @author yiwenli
     * @date 2025/4/14
     */
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param", "value");
    }

    /**
     * @description 可以配置的参数
     * @author yiwenli
     * @date 2025/4/14
     */
    @Validated
    public static class Config {

        @NotEmpty
        private String param;
        @NotEmpty
        private String value;

        public @NotEmpty String getParam() {
            return param;
        }

        public void setParam(@NotEmpty String param) {
            this.param = param;
        }

        public @NotEmpty String getValue() {
            return value;
        }

        public void setValue(@NotEmpty String value) {
            this.value = value;
        }
    }
}
