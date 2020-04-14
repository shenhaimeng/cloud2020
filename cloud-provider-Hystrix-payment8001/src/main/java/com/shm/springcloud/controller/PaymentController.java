package com.shm.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shm.springcloud.entities.CommonResult;
import com.shm.springcloud.entities.Payment;
import com.shm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private EurekaDiscoveryClient discoveryClient;

    @GetMapping(value = "/getPaymentOk/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        return "线程池： "+ Thread.currentThread().getName() + "id:" + id;
    }
    @GetMapping(value = "/getPaymentTimeout/{id}")
    // 当触发断路时 ，Hystrix内部会启线程处理超时以及异常
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            // 条件，这个线程的超时时间为3秒
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="13000")

    })
    // 如果触发熔断，则调用fallbackMethod中的方法
    public String paymentInfo_Timeout(@PathVariable("id") Integer id)
    {
        // 服务方也可以使用断路，正常来说，如果自己的服务超时了，就可以返回一个备胎。
        try{
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+ Thread.currentThread().getName() + "Time out id:" + id;
    }
    private String paymentInfo_TimeOutHandler(Integer id)
    {
        return "熔断的： "+ Thread.currentThread().getName() + "Hystrix id:" + id;
    }
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
    @GetMapping(value = "/payment/circuit/{id}")
    public String paymentCricuitBreaker(@PathVariable("id") Integer id)
    {
        String result =paymentService.paymentCircuitBreaker(id);
        log.info("----result: "+ result);
        return result;
    }
}
