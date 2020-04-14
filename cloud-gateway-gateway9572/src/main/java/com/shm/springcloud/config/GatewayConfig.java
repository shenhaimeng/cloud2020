package com.shm.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author shenhaimeng
 * @date 2020/4/8 - 23:36
 */
@Configuration
public class GatewayConfig {
    // 不仅配置文件可以增加路由配置，在代码中也可以进行配置
    @Bean
    public RouteLocator customRoutelocator(RouteLocatorBuilder builder)
    {
        RouteLocatorBuilder.Builder routers =builder.routes();
        routers.route("id",r -> r.path("/test").uri("http://www.baidu.com")).build();
        return routers.build();
     }
}
