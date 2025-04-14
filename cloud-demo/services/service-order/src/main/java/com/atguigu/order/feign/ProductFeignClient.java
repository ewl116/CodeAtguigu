package com.atguigu.order.feign;

import com.atguigu.order.feign.fallback.ProductFeignClientFallback;
import com.atguigu.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: 商品服务远程调用
 * @Author: yiwenli
 * @Date: 2025/4/9
 */
// TODO 声明这是一个Feign客户端，自动负载均衡
@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    // TODO MVC注解的两套使用逻辑
    // 1、标注在Controller上，是接收这样的请求
    // 2、标注在FeignClient上，是发送这样的请求
//    @GetMapping("/api/product/product/{id}")
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);

}
