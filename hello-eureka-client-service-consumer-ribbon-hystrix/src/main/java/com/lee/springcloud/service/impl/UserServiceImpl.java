package com.lee.springcloud.service.impl;

import com.lee.springcloud.model.User;
import com.lee.springcloud.service.UserService;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author lee
 * @date 2020/4/15 21:19
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 指定了请求合并器,并且指定了请求合并器的窗口为100毫秒
     *
     * @param id
     * @return
     */
//    @Override
//    @HystrixCollapser(batchMethod = "findAll", collapserProperties = {
//            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
//    })
    @HystrixCommand
    public User findOne(Long id) {
        User forObject = restTemplate.postForObject("http://eureka-client-service/user", id, User.class);
        return forObject;
    }

    @HystrixCommand
    @Override
    public List<User> findAll(List<Long> ids) {
        List<User> forObject = restTemplate.postForObject("http://eureka-client-service/users", ids, List.class);
        return forObject;
    }

    public String hiError(Throwable throwable) {
        throwable.printStackTrace();
        return "请求错误!";
    }
}
