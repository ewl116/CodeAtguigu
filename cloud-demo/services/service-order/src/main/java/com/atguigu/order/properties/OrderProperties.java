package com.atguigu.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 订单配置类
 * @Author: yiwenli
 * @Date: 2025/4/8
 */
@Component
// TODO 配置批量绑定在nacos下，可以无需@RefreshScope就能实现自动更新
@ConfigurationProperties(prefix = "order")
@Data
public class OrderProperties {

    String timeout;

    String autoConfirm;

    String dbUrl;

}
