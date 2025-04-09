package com.atguigu.product.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: 商品
 * @Author: yiwenli
 * @Date: 2025/4/7
 */
@Data
public class Product {

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 购买数量
     */
    private int num;

}
