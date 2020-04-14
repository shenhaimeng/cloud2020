package com.shm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author shenhaimeng
 * @date 2020/3/19 - 23:29
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为中策中心时注册服务
public class PaymentMain8004 {
    public static void main(String[] args)
    {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}
