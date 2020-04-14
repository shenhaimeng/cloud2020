package com.shm.springcloud.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author shenhaimeng
 * @date 2020/3/22 - 23:12
 */
@Configuration
public class ApplicationContextConfig {
    /**不能放在@ComponentScan下面     * //ribbon自带的策略有7种
     * RoundRobinRule  轮询  请求次数%服务器，比如第1次请求，2个服务。1%2 = 1 就访问payment1
     * RandomRule      随即
     * RteryRule    轮询失败会尝试重连
     * WeightedResponseTimeRule  响应快的轮询  增加权重信息
     * BestAvaliableRule 会先过滤掉由于多次访问故障而处于断路的状态服务
     * AvailabilityFilteringRule  先过滤故障实例，再选择并发较小的实例
     * ZoneAvoidanceRule 默认规则，复合判断server所在区域性能
     *
     * */
    @Bean
    // @LoadBalanced  该注解就是使用Ribbon自带的策略  上述
    public RestTemplate getRestTemplation()
    {
        return new RestTemplate();
    }
}
