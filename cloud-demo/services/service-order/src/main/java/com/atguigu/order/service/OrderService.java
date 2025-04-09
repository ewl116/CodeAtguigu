package com.atguigu.order.service;

import com.atguigu.order.bean.Order;
import com.atguigu.order.feign.ProductFeignClient;
import com.atguigu.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 订单Service
 * @Author: yiwenli
 * @Date: 2025/4/7
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ProductFeignClient productFeignClient;

    /**
     * @description 创建订单
     * @param productId 商品ID
     * @param userId  用户ID
     * @return {@link Order} 订单
     * @author yiwenli
     * @date 2025/4/7
     */
    public Order createOrder(Long productId, Long userId) {
        Order order = new Order();
        // 获取商品信息
//        Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);
        Product product = productFeignClient.getProductById(productId);
        order.setId(1);
        // 总金额
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("张三");
        order.setAddress("尚硅谷");
        // 远程查询商品列表
        order.setProductList(List.of(product));
        return order;
    }
    
    /**
     * @description 远程获取产品信息（固定只请求第一个，除非出现宕机）
     * @param productId 商品ID
     * @return {@link Product} 商品
     * @author yiwenli
     * @date 2025/4/8
     */
    private Product getProductFromRemote(Long productId) {
        // 1、获取到商品服务所在的所有机器IP+port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        // 2、给远程发送请求
        // 远程URL
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求路径：" + url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    /**
     * @description 进阶2：远程获取产品信息（完成负载均衡的发送请求）
     * @param productId 商品ID
     * @return {@link Product} 商品
     * @author yiwenli
     * @date 2025/4/8
     */
    private Product getProductFromRemoteWithLoadBalancer(Long productId) {
        // 1、选择一个服务地址
        ServiceInstance instance = loadBalancerClient.choose("service-product");
        // 2、给远程发送请求
        // 远程URL
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求路径：" + url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    /**
     * @description 进阶3：远程获取产品信息（基于注解的负载均衡）
     * @param productId 商品ID
     * @return {@link Product} 商品
     * @author yiwenli
     * @date 2025/4/8
     */
    private Product getProductFromRemoteWithLoadBalancerAnnotation(Long productId) {
        // 给远程发送请求：其中service-product会被动态替换
        String url = "http://service-product/product/" + productId;
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

}
