package com.shm.springcloud.service;

import com.shm.springcloud.entities.CommonResult;
import com.shm.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Author shenhaimeng
 * @date 2020/4/4 - 23:07
 */
@Component
public class PaymentFallbackService implements PaymentFeignService {
    @Override
    public CommonResult create(Payment payment) {
        return null;
    }

    @Override
    public CommonResult getPaymentById(Long id) {
        return null;
    }

    @Override
    public String paymentInfo_OK(Integer id) {
        return "---fall bake  ok";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "---fall bake  Timeout";
    }
}
