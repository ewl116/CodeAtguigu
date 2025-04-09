package com.atguigu.order;

import com.atguigu.order.feign.WeatherFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description: 天气调用测试
 * @Author: yiwenli
 * @Date: 2025/4/9
 */
@SpringBootTest
public class WeatherTest {

    @Autowired
    WeatherFeignClient weatherFeignClient;

    @Test
    void weatherTest01() {
        String weather = weatherFeignClient.getWeather("APPCODE 93b7e1986a24c519a7548b17dc16d75",
                "50b53ff8dd7d9fa320d3d3ca32cf8ed1",
                "2182");
        System.out.println(weather);
    }

}
