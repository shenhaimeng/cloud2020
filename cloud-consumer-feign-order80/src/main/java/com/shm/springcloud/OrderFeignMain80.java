package com.shm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author shenhaimeng
 * @date 2020/3/22 - 23:04
 */
@SpringBootApplication
//使用注解需激活注解 一般在主入口处添加
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String[] args)
    {
        SpringApplication.run(OrderFeignMain80.class,args);
    }
}
