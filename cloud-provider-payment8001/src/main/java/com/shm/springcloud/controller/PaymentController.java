package com.shm.springcloud.controller;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.converters.Auto;
import com.shm.springcloud.entities.CommonResult;
import com.shm.springcloud.entities.Payment;
import com.shm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author shenhaimeng
 * @date 2020/3/21 - 0:06
 */
@Slf4j
@RestController
// 具备刷新功能
// 配置文件修改完毕后，curl -X POST "http://localhost:8001/actuator/refresh" 进行配置文件刷新，这样就动态刷新了
@RefreshScope
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;
    @Autowired
    private EurekaDiscoveryClient discoveryClient;
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        System.out.println("插入数据库");
        if(result > 0)
        {
            return new CommonResult(200,"插入数据成功",result);
        }
        else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping(value = "/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id)
    {
        Payment result = paymentService.getPaymentById(id);
        if(result != null)
        {
            System.out.println("查出数据：" + result.toString());
            return new CommonResult(200,"查询数据成功" + port,result);
        }
        else {
            return new CommonResult(444,"查询数据失败" + port,null);
        }
    }

    @GetMapping(value="/getinstance")
    public Object discover () {
        List<String> services =  discoveryClient.getServices();
        for(String element:services)
        {
            log.info("*****"+ element);
        }
        List<ServiceInstance> instances =  discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance:instances)
        {
            log.info("*****"+ instance.getInstanceId() + "******" + instance.getHost() + "****"+instance.getUri()+"****"+instance.getPort());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return port;
    }
}
