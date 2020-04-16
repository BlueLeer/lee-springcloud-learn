package com.lee.springcloud.command;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.web.client.RestTemplate;

/**
 * @author lee
 * @date 2020/4/15 14:54
 */
public class HiCommand extends HystrixCommand<String> {
    private RestTemplate template;
    private Long keyId;
    public static final String HI_KEY = "Lee-Command";

    public HiCommand(RestTemplate restTemplate, Long keyId) {
        // 我们指定了命令的分组名称、命令名称、以及线程池名称
        // 如果这里不指定,则命令名称默认以类名作为命令名称
        // 对请求命令属性的设置有2种方式:
        // 1.继承的时候,可以使用Setter对象来对请求的命令的属性进行设置
        // 2.使用注解的方式实现的时候,通过@HystrixCommand中的commandProperties属性进行设置(详见注解配置)
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Lee-Group"))
                .andCommandKey(HystrixCommandKey.Factory.asKey(HI_KEY))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("Lee-Thread"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(200)));
        this.template = restTemplate;
        this.keyId = keyId;
    }

    /**
     * 命令真正的执行逻辑
     *
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        String url = "http://eureka-client-service/hi";
        String result = template.getForObject(url, String.class);
        return result;
    }

    @Override
    protected String getFallback() {
        Throwable exception = getExecutionException();
        // 根据不同的异常,我们可以调用不同的处理逻辑
        return super.getFallback();
    }

    /**
     * 开启缓存
     *
     * @return
     */
    @Override
    protected String getCacheKey() {
        // super.getCacheKey();父类的默认实现中是返回null的,也就是说没有开启缓存,
        // 开启缓存很简单:只用返回当前命令的Id即可
        // 当不同的外部请求调用了同一个依赖服务的时候,Hystrix会根据getCacheKey方法的返回值来区分它们是否重复的请求
        return String.valueOf(this.keyId);
    }

    /**
     * 刷新缓存,根据Command的ID进行刷新
     * 当一个方法对该命令对应的缓存产生影响的时候,可以调用该方法,即可刷新缓存
     */
    public static void flushCache(Long keyId) {
        HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey(HI_KEY), HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(keyId));
    }

}
