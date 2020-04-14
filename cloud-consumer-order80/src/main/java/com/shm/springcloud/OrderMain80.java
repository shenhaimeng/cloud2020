package com.shm.springcloud;

import com.shm.myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author shenhaimeng
 * @date 2020/3/22 - 23:04
 */
@SpringBootApplication
@EnableEurekaClient
// MyselfRule 这个为什么不会扫那？因为ComponentScan会扫描主启动类下面的文件夹
//改成自定义的规则
//@RibbonClient(name="cloud-payment-service",configuration = MyselfRule.class)
public class OrderMain80 {
    public static void main(String[] args)
    {
        SpringApplication.run(OrderMain80.class,args);
    }
}
