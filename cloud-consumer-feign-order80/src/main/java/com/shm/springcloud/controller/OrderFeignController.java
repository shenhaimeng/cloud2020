package com.shm.springcloud.controller;

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
}
