package com.lee.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author WangLe
 * @date 2019/7/18 11:38
 * @description 示例:hello-eureka-config-client 模块的配置文件放在此配置中心进行管理

 *
 * 例如:
 * http://localhost:8888/eureka-config/hello-eureka-client-service
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class EurekaConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConfigApplication.class, args);
    }

}
