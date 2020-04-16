package com.lee.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author WangLe
 * @date 2019/7/17 17:24
 * @description 通过 @FeignClient(value = "eureka-client-service") 指定服务的实例名,服务名不区分大小写
 */
@FeignClient(value = "eureka-client-service", fallback = HiServiceFallback.class)
public interface HiService {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String hi();

    @RequestMapping(value = "/hiFeign", method = RequestMethod.GET)
    String hiFeign(@RequestParam("name") String name);

    @PostMapping(value = "/hiPost")
    String hiPost();
}
