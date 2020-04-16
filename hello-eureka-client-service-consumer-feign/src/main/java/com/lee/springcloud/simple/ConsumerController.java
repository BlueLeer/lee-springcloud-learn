package com.lee.springcloud.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign继承的特性
 *
 * @author lee
 * @date 2020/4/16 16:27
 */
@RestController
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("/simple")
    public String simple() {
        String hi = consumerService.hi();
        String name = consumerService.hiFeign("wangle");
        return hi + "##########" + name;
    }
}
