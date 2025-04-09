package com.atguigu.order.bean;

import com.atguigu.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 订单
 * @Author: yiwenli
 * @Date: 2025/4/7
 */
@Data
public class Order {

    /**
     * 订单ID
     */
    private long id;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 下单用户ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 派送地址
     */
    private String address;

    /**
     * 商品列表
     */
    private List<Product> productList;

}
