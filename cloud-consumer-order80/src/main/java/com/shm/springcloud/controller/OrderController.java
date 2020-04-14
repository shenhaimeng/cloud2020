package com.shm.springcloud.controller;

import com.netflix.discovery.converters.Auto;
import com.shm.springcloud.entities.CommonResult;
import com.shm.springcloud.entities.Payment;
import com.shm.springcloud.lb.LoadBalancerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    @Autowired
    private LoadBalancerImpl loadBalancerImpl;

    @Autowired
    private EurekaDiscoveryClient discoveryClient;

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

    @GetMapping("/consumer/getForEntity/{id}")
    public CommonResult<Payment> getPayment1(@PathVariable("id") Long id)
    {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/getPaymentById/" + id,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful())
        {
            return entity.getBody();
        }
        else {
            return new CommonResult<>(444,"操作超时");
        }

    }
    @GetMapping("/consumer/paymentForEntity/create")
    public CommonResult<Payment> create1(Payment payment)
    {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful())
        {
            return entity.getBody();
        }
        else {
            return new CommonResult<>(444,"操作超时");
        }
    }

    private ServiceInstance getServiceInstance()
    {
        List<ServiceInstance> instances =  discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances ==null || instances.size()<=0)
        {
            return null;
        }
        return loadBalancerImpl.instance(instances);
    }

    @GetMapping(value = "/consumer/payment/lb")
    private String getPaymentLB()
    {
        List<ServiceInstance> instances =  discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances ==null || instances.size()<=0)
        {
            return null;
        }
        ServiceInstance serviceInstance =  loadBalancerImpl.instance(instances);
        System.out.println(serviceInstance.getUri() + "~~~~" + serviceInstance.getPort());
        // url 中包含地址加端口
        return restTemplate.getForObject(serviceInstance.getUri()+"/payment/lb",String.class);
    }
}
