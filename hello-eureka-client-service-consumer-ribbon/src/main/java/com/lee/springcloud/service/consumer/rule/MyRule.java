package com.lee.springcloud.service.consumer.rule;

import com.netflix.loadbalancer.ClientConfigEnabledRoundRobinRule;
import com.netflix.loadbalancer.Server;

/**该策略较为特殊,我们一般不直接使用它,在ClientConfigEnabledRoundRobinRule这个类中choose的默认实施是使用了roundRobinRule的线性
 * 轮询机制;我们可以通过继承该策略自定义负载均衡策略,在我们遇到一些无法实施的时候,就可以调用super.choose(key)方法来实施
 * @author lee
 * @date 2020/4/14 14:41
 */
public class MyRule extends ClientConfigEnabledRoundRobinRule {
    @Override
    public Server choose(Object key) {
        
        return super.choose(key);
    }
}
