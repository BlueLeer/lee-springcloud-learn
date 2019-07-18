package com.lee.springcloud.service;

import com.lee.springcloud.service.impl.HiServiceErrorImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author WangLe
 * @date 2019/7/17 17:51
 * @description
 */

@FeignClient(value = "eureka-client-service", fallback = HiServiceErrorImpl.class)
public interface HiService {

    @GetMapping("/hi")
    String hi();

}
