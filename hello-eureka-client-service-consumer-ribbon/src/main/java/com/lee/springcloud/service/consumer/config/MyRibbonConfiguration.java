package com.lee.springcloud.service.consumer.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lee
 * @date 2020/4/14 15:37
 */
// 实现更细粒度的客户端配置,专门为eureka-client-service这个服务的配置(这个注解组合了@Configuration注解)
@RibbonClient(name = "eureka-client-service", configuration = EurekaClientServiceConfiguration.class)
public class MyRibbonConfiguration {
}
