package com.lee.springcloud.service.consumer.controller;

import com.lee.springcloud.service.consumer.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangLe
 * @date 2019/7/17 12:40
 * @description
 */
@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String hi() {
        return hiService.getHiMessage();
    }
}
