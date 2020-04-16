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

import java.util.Arrays;
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
     * 注意加上scope:com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,不然会报空指针错误,原因还未知
     *
     * @param id
     * @return
     */
    @Override
    @HystrixCollapser(batchMethod = "findAll", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "800")}
            , scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL)
    public User findOne(Long id) {
        return null;
    }

    @HystrixCommand
    @Override
    public List<User> findAll(List<Long> ids) {
        // 这里使用数组接收,然后转换成列表,不然会报错
        // 解决办法参考:https://blog.csdn.net/x7418520/article/details/89360124
        // 参考:http://www.imooc.com/article/35116 (页面中搜索:LinkedHashMap)
        User[] forObject = restTemplate.getForObject("http://eureka-client-service/users?ids={1}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(forObject);
    }

    public String hiError(Throwable throwable) {
        throwable.printStackTrace();
        return "请求错误!";
    }
}
