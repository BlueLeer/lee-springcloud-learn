package com.lee.springcloud.controller;

import com.lee.springcloud.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangLe
 * @date 2019/7/17 17:27
 * @description
 */
@Controller
public class HiController {

    @Autowired
    private HiService hiService;

    @GetMapping(value = "/hi")
    @ResponseBody
    public String hi() {
        return hiService.hi();
    }
    
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}

