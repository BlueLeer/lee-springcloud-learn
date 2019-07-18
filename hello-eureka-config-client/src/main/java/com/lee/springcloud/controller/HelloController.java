package com.lee.springcloud.controller;

import com.lee.springcloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangLe
 * @date 2019/7/18 14:17
 * @description 显式指定返回的数据格式为json的原因是:EurekaServer集成了jackson-dataformat-xml这个依赖,所以为了避免有时候返回的数据格式是xml格式的,所以这里指定下
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HelloController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private User user;

    @GetMapping(value = "/hello")
    public String hello() {
        return String.format("你好,我是[%s],我服务的端口号是[%s]", appName, serverPort);
    }

    @GetMapping(value = "/user")
    public User user() {
        return user;
    }

}
