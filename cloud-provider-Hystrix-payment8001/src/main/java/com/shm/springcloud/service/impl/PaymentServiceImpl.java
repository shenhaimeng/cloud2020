package com.shm.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shm.springcloud.dao.PaymentDao;
import com.shm.springcloud.entities.Payment;
import com.shm.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author shenhaimeng
 * @date 2020/3/20 - 23:48
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    //  ==== 服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            // 熔断开关打开
            @HystrixProperty(name = "circuitBreaker.enabled",value="true"),
            // 请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),
            // 时间窗口期、或时间范围
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="10000"),
            // 失败了达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="60")
//上面配置意思为： 在时间范围内，10次请求中60%失败，将触发熔断。。。。参数
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id<0)
        {
            throw  new RuntimeException("---id 不能为负");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() +" 调用成功，流水号："+serialNumber;

    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "---id 不能为负" + id;
    }
}
