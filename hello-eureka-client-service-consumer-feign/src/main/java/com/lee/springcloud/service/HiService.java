package com.lee.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author WangLe
 * @date 2019/7/17 17:24
 * @description 通过 @FeignClient(value = "eureka-client-service") 指定服务的实例名
 */
@FeignClient(value = "eureka-client-service")
public interface HiService {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String hi();
}
