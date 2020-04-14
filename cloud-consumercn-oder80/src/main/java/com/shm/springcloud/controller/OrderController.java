package com.shm.springcloud.controller;

import com.shm.springcloud.entities.CommonResult;
import com.shm.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author shenhaimeng
 * @date 2020/3/22 - 23:08
 */
@RestController
@Slf4j
public class OrderController {
    //RestTemplate 调用方式接口的封装，类似于httpClient，访问restful接口简单粗暴

    public static final String PAYMENT_URL= "http://cloud-payment-service";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment)
    {
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/getPaymentById/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(PAYMENT_URL+"/getPaymentById/" + id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/consul")
    public String getConsulInfo()
    {
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
    }
}
