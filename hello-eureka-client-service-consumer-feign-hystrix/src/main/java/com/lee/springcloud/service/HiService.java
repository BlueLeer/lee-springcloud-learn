package com.lee.springcloud.service;

import com.lee.springcloud.service.impl.HiServiceErrorImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** HiService服务降级
 * @author WangLe
 * @date 2019/7/17 17:51
 * @description
 */

@FeignClient(value = "eureka-client-service", fallback = HiServiceErrorImpl.class)
public interface HiService {

    @GetMapping("/hi")
    String hi();

    @RequestMapping(value = "/hiFeign", method = RequestMethod.GET)
    String hiFeign(@RequestParam("name") String name);

}
