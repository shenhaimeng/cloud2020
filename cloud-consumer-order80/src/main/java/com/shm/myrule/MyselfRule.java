package com.shm.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author shenhaimeng
 * @date 2020/3/30 - 22:46
 */
@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule()
    {
        return new RandomRule();
    }
}
