package com.shm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import sun.applet.Main;

/**
 * @Author shenhaimeng
 * @date 2020/4/2 - 22:50
 */
@SpringBootApplication
@EnableEurekaClient
// 激活断路器，回路
@EnableCircuitBreaker
public class PaymentHystrixMain8001 {
    public static void main(String[] args)
    {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }
}
