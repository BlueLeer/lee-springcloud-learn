package com.lee.springcloud.eurekaclient.simple;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign继承的特性
 *
 * @author lee
 * @date 2020/4/16 16:27
 */
@RestController
public class ProviderController implements CommonService {
    @Override
    public String hi() {
        return "Consumer: hi";
    }

    @Override
    public String hiFeign(@RequestParam("name") String name) {
        return "Consumer: hiFeign, " + name;
    }
}
