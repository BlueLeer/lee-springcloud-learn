package com.lee.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author lee
 * @date 2020/4/16 15:11
 */
@SpringCloudApplication
@EnableTurbine
public class HystrixDashboardTurbine {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardTurbine.class, args);
    }
}
