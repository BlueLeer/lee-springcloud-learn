package com.lee.springcloud;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author WangLe
 * @date 2019/7/17 17:23
 * @description 使用Feign的方式来进行远程服务调用
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerFeign {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeign.class, args);
    }

    /**
     * 默认情况下,Feign客户端默认的Logger.Level定义为NONE级别,该级别不会记录任何Feign调用过程中的信息,所以我们需要调整它的级别,它是针对全局的日志级别
     * Feign的日志级别有以下四种:
     * 1.NONE:不记录任何日志
     * 2.BASIC:仅记录请求方法 URL以及相应状态码和执行时间
     * 3.HEADERS:除了记录BASIC级别的信息之外,还会记录请求和响应头信息
     * 4.FULL:记录所有请求与响应明细,包括头信息/请求体/元数据
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
