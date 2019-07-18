package com.lee.springcloud.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangLe
 * @date 2019/7/17 9:39
 * @description
 */
@RestController
public class HelloController {
    @Value("${spring.application.name}")
    private String name;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi() {
        return String.format("hello，[%s:%s]为您提供服务!", name, port);
    }
}
