package com.atguigu.order.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 全局异常处理器
 * @Author: yiwenli
 * @Date: 2025/4/9
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(Throwable.class)
    public String error(Throwable e) {
        return "";
    }*/
}
