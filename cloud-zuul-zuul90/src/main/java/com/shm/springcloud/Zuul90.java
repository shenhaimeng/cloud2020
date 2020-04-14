package com.shm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author shenhaimeng
 * @date 2020/4/6 - 23:30
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class Zuul90
{
    public static void main(String[] args)
    {
        SpringApplication.run(Zuul90.class,args);
    }
}
