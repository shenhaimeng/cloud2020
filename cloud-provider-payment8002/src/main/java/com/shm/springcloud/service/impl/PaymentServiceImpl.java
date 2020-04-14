package com.shm.springcloud.service.impl;

import com.shm.springcloud.dao.PaymentDao;
import com.shm.springcloud.entities.Payment;
import com.shm.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
