package com.lee.springcloud.service.consumer.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lee
 * @date 2020/4/14 16:00
 */
@Configuration
public class EurekaClientServiceConfiguration {
//    @Bean
//    public IPing ribbonPing() {
//        return new PingUrl();
//    }
}
