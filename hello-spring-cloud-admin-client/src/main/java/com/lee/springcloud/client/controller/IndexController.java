package com.lee.springcloud.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangLe
 * @date 2019/7/18 17:00
 * @description
 */
@RestController
public class IndexController {
    @RequestMapping("/index")
    public String index(String message) {
        return String.format("收到你的信息[%s],我很开心呦!", message);
    }
}
