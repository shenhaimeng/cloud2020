package com.shm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @Author shenhaimeng
 * @date 2020/3/22 - 23:04
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderCSMain80 {
    public static void main(String[] args)
    {
        SpringApplication.run(OrderCSMain80.class,args);
    }
}
