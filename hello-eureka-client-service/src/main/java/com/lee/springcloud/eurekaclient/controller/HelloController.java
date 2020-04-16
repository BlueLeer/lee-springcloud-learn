package com.lee.springcloud.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author WangLe
 * @date 2019/7/17 9:39
 * @description
 */
@RestController
public class HelloController {
    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi() {
        // 随机阻塞一段时间(Hystrix的默认超时时间为2000毫秒)
        int randomTimeout = new Random().nextInt(20);
        System.out.println("GET#####Eureka Client handle time: " + randomTimeout);
        try {
            TimeUnit.SECONDS.sleep(randomTimeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("1111hello，[appName: %s,instanceId: %s]为您提供服务!", "appName", "instanceId");
    }

    @PostMapping(value = "/hiPost")
    public String hiPost() {
        // 随机阻塞一段时间(Hystrix的默认超时时间为2000毫秒)
        int randomTimeout = new Random().nextInt(50000);
        System.out.println("POST#####Eureka Client handle time: " + randomTimeout);
        try {
            TimeUnit.SECONDS.sleep(randomTimeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("1111hello，[appName: %s,instanceId: %s]为您提供服务!", "appName", "instanceId");
    }

    @RequestMapping(value = "/hiFeign", method = RequestMethod.GET)
    public String hiFeign(String name) {
        // 随机阻塞一段时间(Hystrix的默认超时时间为2000毫秒)

        return String.format("1111hello [%s]，[app: %s,port: %s]为您提供服务!", name, appName, port);
    }
}
