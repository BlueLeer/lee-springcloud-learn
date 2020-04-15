package com.lee.springcloud.service.impl;

import com.lee.springcloud.model.User;
import com.lee.springcloud.service.CacheService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author lee
 * @date 2020/4/15 18:08
 */
@Component
public class CacheServiceImpl implements CacheService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @return
     * @CacheResult 这个命令会开启缓存, 所以Hystrix会将请求结果放入到缓存当中去, 它缓存的Key会使用方法上面所有的参数, 这里就是id值了(这里未生效?why?)
     */
    @HystrixCommand
    @CacheResult
    @Override
    public String getMessageById(@CacheKey("id") User user) {
        String uri = "http://eureka-client-service/hi";
        return restTemplate.getForEntity(uri, String.class).getBody();
    }
}
