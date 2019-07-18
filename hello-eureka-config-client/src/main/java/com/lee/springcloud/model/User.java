package com.lee.springcloud.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author WangLe
 * @date 2019/7/18 14:40
 * @description 目的是从分布式配置中心 hello-eureka-config 下的application-hello-eureka-client-service.yml总读取配置文件
 */
@Component
@ConfigurationProperties(prefix = "lee")
public class User implements Serializable {
    private String username;
    private Integer age;
    private Double height;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public User() {
    }

    public User(String username, Integer age, Double height) {
        this.username = username;
        this.age = age;
        this.height = height;
    }
}
