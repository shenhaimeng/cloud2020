package com.shm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author shenhaimeng
 * @date 2020/3/23 - 22:55
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002 {
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaMain7002.class,args);
    }
}
