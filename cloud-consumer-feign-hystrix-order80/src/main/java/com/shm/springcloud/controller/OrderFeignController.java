package com.shm.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shm.springcloud.entities.CommonResult;
import com.shm.springcloud.entities.Payment;
import com.shm.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shenhaimeng
 * @date 2020/3/22 - 23:08
 */
@RestController
@Slf4j
//全局服务降级
@DefaultProperties(defaultFallback = "paymentInfo_AllTimeOutHandler")
public class OrderFeignController {


    @Autowired
    private PaymentFeignService paymentFeignService;
    //RestTemplate 调用方式接口的封装，类似于httpClient，访问restful接口简单粗暴


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment)
    {
        return paymentFeignService.create(payment);
    }

    @GetMapping("/consumer/getPaymentById/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id)
    {
        return paymentFeignService.getPaymentById(id);
    }
    @GetMapping(value = "/consumer/getPaymentOk/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        return paymentFeignService.paymentInfo_OK(id);
    }
    @GetMapping(value = "/consumer/getPaymentTimeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
//            // 条件，这个线程的超时时间为3秒
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="2000")
//
//    })
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id)
    {
        int a = 10/0;
        return paymentFeignService.paymentInfo_Timeout(id);
    }
    // 高压时，tomcat中的线程被占完，故效率变低


    private String paymentInfo_TimeOutHandler(Integer id)
    {
        return "异常";
    }

    // 全局不能有参数？？
    public String paymentInfo_AllTimeOutHandler()
    {
        return "全局断路器";
    }

}
