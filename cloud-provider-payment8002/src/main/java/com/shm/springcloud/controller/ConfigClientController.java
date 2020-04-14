package com.shm.springcloud.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shenhaimeng
 * @date 2020/4/10 - 0:21
 */
@RestController
// 配置文件修改完毕后，curl -X POST "http://localhost:8001/actuator/refresh" 进行配置文件刷新，这样就动态刷新了
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
