package com.lee.springcloud.service.impl;

import com.lee.springcloud.service.HiService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
    @HystrixCommand(fallbackMethod = "hiDefault")
    public String hi() {
        String uri = "http://eureka-client-service/hi";
        return restTemplate.getForEntity(uri, String.class).getBody();
    }

    /**
     * 注意:指定了fallback方法,必须保证该降级方法也在该类中(方法修饰符没有限制,private/protect/public均可)
     * <p>
     * 异步的方式执行
     *
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "hiDefault")
    public Future<String> hi2() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                String uri = "http://eureka-client-service/hi";
                return restTemplate.getForEntity(uri, String.class).getBody();
            }
        };
    }

    /**
     * 通过指定:ignoreExceptions为RuntimeException,当发生RuntimeException类型的异常时,Hystrix会将其包装成HystrixBadRequestException
     * 这样就不会触发服务降级了
     *
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "hiDefault", ignoreExceptions = RuntimeException.class)
    public Future<String> hi3() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                String uri = "http://eureka-client-service/hi";
                return restTemplate.getForEntity(uri, String.class).getBody();
            }
        };
    }

    /**
     * 指定了commandKey、groupKey、threadPoolKey。如果不指定ThreadPoolKey，那么相同的groupKey的命令会在同一个线程池中
     * 如果指定了ThreadPoolKey时，则相同的ThreadPoolKey的命令会在同一个线程池当中
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "hiDefault",commandKey = "hi4",groupKey = "hi-group",threadPoolKey = "hi-thread")
    public String hi4() {
        String uri = "http://eureka-client-service/hi";
        return restTemplate.getForEntity(uri, String.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiDefault() {
        // 该降级方法依然不可靠,依然会出现请求超时的情况(用睡眠5秒钟来模拟)
        // 当该降级方法因为超时,或者错误,我们依然可以为它添加@HystrixCommand来为它生成命令,让它也纳入Hystrix的监控之下
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hi default!";
    }

    /**
     * 当该降级方法被触发的时候,throwable就会被赋予对应的异常
     *
     * @param throwable
     * @return
     */
    private String hiError(Throwable throwable) {
        System.out.println("####" + throwable.getMessage());
        return "出错啦!";
    }
}
