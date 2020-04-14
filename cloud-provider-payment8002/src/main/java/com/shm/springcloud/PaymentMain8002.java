package com.shm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author shenhaimeng
 * @date 2020/3/19 - 23:29
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8002 {
    public static void main(String[] args)
    {
        SpringApplication.run(PaymentMain8002.class,args);
    }
}
