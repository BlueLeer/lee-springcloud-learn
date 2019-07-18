package com.lee.springcloud.service.consumer.service.impl;

import com.lee.springcloud.service.consumer.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author WangLe
 * @date 2019/7/17 12:38
 * @description
 */

@Service
public class HiServiceImpl implements HiService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getHiMessage() {
        // 使用服务名获取
        String uri = "http://EUREKA-CLIENT-SERVICE/hi";
        String body = restTemplate.getForEntity(uri, String.class).getBody();

        return body;
    }
}
