package com.lee.springcloud.controller;

import com.lee.springcloud.model.User;
import com.lee.springcloud.service.CacheService;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * 示例Hystrix中缓存的使用
 *
 * @author lee
 * @date 2020/4/15 18:06
 */
@RestController
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @GetMapping("/cache")
    public String cache(Long id) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        String messageById = cacheService.getMessageById(new User(id,"zhangsan"));
        return messageById;

    }
}
