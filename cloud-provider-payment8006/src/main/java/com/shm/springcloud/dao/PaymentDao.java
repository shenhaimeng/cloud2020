package com.shm.springcloud.dao;

import com.shm.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Author shenhaimeng
 * @date 2020/3/20 - 23:28
 */
@Mapper
@Component
public interface PaymentDao {
     public int create(Payment payment);

     public Payment getPaymentById(@Param("id") Long id);
}
