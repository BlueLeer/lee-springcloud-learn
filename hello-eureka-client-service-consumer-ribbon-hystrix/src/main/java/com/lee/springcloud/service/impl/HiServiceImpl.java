package com.lee.springcloud.service.impl;

import com.lee.springcloud.service.HiService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author WangLe
 * @date 2019/7/17 18:20
 * @description
 */

@Service
public class HiServiceImpl implements HiService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi() {
        String uri = "http://eureka-client-service/hi";
        return restTemplate.getForEntity(uri, String.class).getBody();
    }

    public String hiError() {
        return "出错啦!!!!!";
    }
}
