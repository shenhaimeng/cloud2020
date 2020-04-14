package com.shm.springcloud.service;

import com.shm.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author shenhaimeng
 * @date 2020/3/20 - 23:59
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
