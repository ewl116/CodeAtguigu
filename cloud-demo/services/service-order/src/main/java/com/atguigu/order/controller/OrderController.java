package com.atguigu.order.controller;

import com.atguigu.order.bean.Order;
import com.atguigu.order.properties.OrderProperties;
import com.atguigu.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 订单Controller
 * @Author: yiwenli
 * @Date: 2025/4/7
 */
@Slf4j
// TODO @RefreshScope配合@Value使用实现配置的自动更新
//@RefreshScope
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

    /*@Value("${order.timeout}")
    String orderTimeout;
    @Value("${order.auto-confirm}")
    String orderAutoConfirm;*/

    @RequestMapping("/config")
    public String config() {
        return "order.timeout=" + orderProperties.getTimeout() + ";" +
                "order.auto-confirm=" + orderProperties.getAutoConfirm() + ";" +
                "order.db-url=" + orderProperties.getDbUrl();
    }

    /**
     * @description 创建订单
     * @param
     * @return
     * @author yiwenli
     * @date 2025/4/7
     */
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        return order;
    }

    /**
     * @description 秒杀
     * @param
     * @return
     * @author yiwenli
     * @date 2025/4/10
     */
    @GetMapping("/seckill")
    public Order seckill(@RequestParam("userId") Long userId,
                         @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    @GetMapping("/writeDb")
    public String writeDb() {
        return "writeDb success......";
    }

    @GetMapping("/readDb")
    public String readDb() {
        log.info("readDb");
        return "readDb success......";
    }

}
