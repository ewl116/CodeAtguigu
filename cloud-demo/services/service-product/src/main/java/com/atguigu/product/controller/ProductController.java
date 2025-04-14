package com.atguigu.product.controller;

import com.atguigu.product.bean.Product;
import com.atguigu.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 商品Controller
 * @Author: yiwenli
 * @Date: 2025/4/7
 */
@RestController
//@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * @description 查询商品
     * @param
     * @return
     * @author yiwenli
     * @date 2025/4/7
     */
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId,
                              HttpServletRequest request) {
        System.out.println(request.getHeader("X-Token"));
        System.out.println("hello");
        Product product = productService.getProduct(productId);
        return product;
    }
}
