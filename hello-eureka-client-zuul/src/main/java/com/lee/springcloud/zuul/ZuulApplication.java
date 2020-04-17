package com.lee.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author WangLe
 * @date 2019/7/18 10:01
 * @description 该服务只是作为路由转发和过滤用.路由是微服务的一部分.Zuul和Ribbon结合实现了负载均衡的作用
 * 说明:@@EnableZuulProxy 和 @EnableZuulServer 的区别:
 * EnableZuulServer注解是高配版本
 * EnableZuulProxy注解是低配版本
 * 如果不想让高版本多出的过滤器生效，可用低配版本注解
 * 低配版本注解更适合自定义过滤器，因为经过的过滤器少，性能会比较高
 */
@SpringBootApplication
@EnableEurekaClient // 让zuul也成为eureka的客户端,这样zuul也能获取到eureka维护的服务清单,以实现path到serviceId之间的映射,再从服务清单中挑选实例来进行请求的转发
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
