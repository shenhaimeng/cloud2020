package com.shm.springcloud.controller;

import com.shm.springcloud.entities.CommonResult;
import com.shm.springcloud.entities.Payment;
import com.shm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author shenhaimeng
 * @date 2020/3/21 - 0:06
 */
@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        System.out.println("插入数据库");
        if(result > 0)
        {
            return new CommonResult(200,"插入数据成功" + port,result);
        }
        else {
            return new CommonResult(444,"插入数据失败" +port,null);
        }
    }

    @GetMapping(value = "/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id)
    {
        Payment result = paymentService.getPaymentById(id);
        if(result != null)
        {
            System.out.println("查出数据：" + result.toString());
            return new CommonResult(200,"查询数据成功" +port ,result);
        }
        else {
            return new CommonResult(444,"查询数据失败"+port,null);
        }
    }

    @RequestMapping(value = "/payment/zk")
    public String paymentzk()
    {
        return "Springcloud with zookeeper:" + port +"\t" + UUID.randomUUID().toString();
    }
}
