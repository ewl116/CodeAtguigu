package com.atguigu.order.feign.fallback;

import com.atguigu.order.feign.ProductFeignClient;
import com.atguigu.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Description: 商品服务远程调用兜底
 * @Author: yiwenli
 * @Date: 2025/4/9
 */
@Component
public class ProductFeignClientFallback implements ProductFeignClient {

    @Override
    public Product getProductById(Long id) {
        System.out.println("商品服务根据ID获取商品信息兜底回调......");
        Product product = new Product();
        product.setId(id);
        product.setPrice(new BigDecimal("0"));
        product.setProductName("未知商品");
        product.setNum(0);
        return product;
    }
}
