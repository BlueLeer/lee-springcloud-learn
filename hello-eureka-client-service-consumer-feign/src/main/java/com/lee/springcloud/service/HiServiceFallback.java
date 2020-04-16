package com.lee.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 2020/4/16 23:25
 */
@Component
public class HiServiceFallback implements HiService {
    @Override
    public String hi() {
        return "熔断了.....";
    }

    @Override
    public String hiFeign(String name) {
        return null;
    }

    @Override
    public String hiPost() {
        return null;
    }
}
