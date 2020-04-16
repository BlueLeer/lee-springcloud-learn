package com.lee.springcloud.controller;

import com.lee.springcloud.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/hiFeign")
    @ResponseBody
    public String hiFeign(@RequestParam("name") String name) {
        return hiService.hiFeign(name);
    }
    
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    
    

}

