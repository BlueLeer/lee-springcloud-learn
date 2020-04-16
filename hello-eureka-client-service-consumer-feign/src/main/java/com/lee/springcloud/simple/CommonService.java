package com.lee.springcloud.simple;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign继承的特性
 * 公共的类,开发的时候,它在服务提供者和服务消费者的公共依赖部分,这里为了方便,分别放在服务提供者和提供者里面,他们都是一样的
 *
 * @author lee
 * @date 2020/4/16 16:22
 */
@RequestMapping("/simple")
public interface CommonService {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String hi();

    @RequestMapping(value = "/hiFeign", method = RequestMethod.GET)
    String hiFeign(@RequestParam("name") String name);
}
