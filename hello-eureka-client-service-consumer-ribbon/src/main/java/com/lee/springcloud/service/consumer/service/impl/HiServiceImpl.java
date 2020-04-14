package com.lee.springcloud.service.consumer.service.impl;

import com.lee.springcloud.service.consumer.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangLe
 * @date 2019/7/17 12:38
 * @description
 */

@Service
public class HiServiceImpl implements HiService {

    @Autowired
    private RestTemplate restTemplate;

    /**GET请求的发起方式:getForEntity和getForObject两种方式
     * 通过RestTemplate进行服务调用,返回为请求响应体中body的包装,需要指定类型
     * @return
     */
    @Override
    public String getHiMessage() {
        // 使用服务名获取
        String url = "http://EUREKA-CLIENT-SERVICE/hi";
        String body = restTemplate.getForEntity(url, String.class).getBody();
//
//        // 方式二,如果url后面带有路径参数:注意是从1开始的
        String url2 = "http://EUREKA-CLIENT-SERVICE/hi?name={1}";
        restTemplate.getForEntity(url2, String.class, "lee");
//
//        // 方式三
        String url3 = "http://EUREKA-CLIENT-SERVICE/hi?name={name}";
        Map<String, String> params = new HashMap<>();
        params.put("name", "zhangsan");
        restTemplate.getForEntity(url3, String.class, params);
        
        // 方式四:URI访问的方式
        ResponseEntity<String> forEntity = restTemplate.getForEntity(URI.create("http://EUREKA-CLIENT-SERVICE/hi"), String.class);

        return forEntity.getBody();
    }

    /**
     * POST请求的发起方式有三种:postForEntity,postForObject,postForLocation(相当于指定了返回类型为URI)
     */
    public void post(){
        
    }
}
