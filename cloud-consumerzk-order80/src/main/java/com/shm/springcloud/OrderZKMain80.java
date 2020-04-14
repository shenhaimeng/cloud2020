package com.shm.springcloud;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
    import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author shenhaimeng
 * @date 2020/3/22 - 23:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class OrderZKMain80 {
    public static void main(String[] args)
    {
        SpringApplication.run(OrderZKMain80.class,args);
    }
}
