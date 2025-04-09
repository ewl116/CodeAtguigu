package com.atguigu.product.service;

import com.atguigu.product.bean.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Description: 商品Service
 * @Author: yiwenli
 * @Date: 2025/4/7
 */
@Service
public class ProductService {

    /**
     * @description 查询商品
     * @param
     * @return
     * @author yiwenli
     * @date 2025/4/7
     */
    public Product getProduct(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("苹果" + productId);
        product.setNum(2);
        return product;
    }

}
