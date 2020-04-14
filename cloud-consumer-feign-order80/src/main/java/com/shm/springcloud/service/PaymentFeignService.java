package com.shm.springcloud.service;

import com.shm.springcloud.entities.CommonResult;
import com.shm.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author shenhaimeng
 * @date 2020/3/31 - 23:22
 */
@Component
@FeignClient(value = "cloud-payment-service")
//对方暴露什么，这边调用什么,去找那个服务里面的哪个接口
public interface PaymentFeignService {
    @PostMapping(value = "/payment/create")
    CommonResult create(Payment payment);

    // CommonResult<Payment> getPaymentById(@Param("id") Long id);
    @GetMapping(value = "/getPaymentById/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);

}
