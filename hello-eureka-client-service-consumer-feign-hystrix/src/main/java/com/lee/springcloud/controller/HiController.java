package com.lee.springcloud.controller;

import com.lee.springcloud.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangLe
 * @date 2019/7/17 17:54
 * @description
 */
@RestController
public class HiController {


    @Autowired
    private HiService hiService;

    @GetMapping(value = "/hi")
    public String hi() {
        return hiService.hi();
    }

    @GetMapping(value = "/hiHystrix")
    public String hiHystrix(String name) {
        return hiService.hiFeign(name);
    }
}
