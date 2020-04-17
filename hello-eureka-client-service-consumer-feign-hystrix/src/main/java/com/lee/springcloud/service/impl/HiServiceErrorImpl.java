package com.lee.springcloud.service.impl;

import com.lee.springcloud.service.HiService;
import org.springframework.stereotype.Component;

/**
 * @author WangLe
 * @date 2019/7/17 18:06
 * @description 当熔断器被激活的时候会调用相应的实现方法
 */

@Component
public class HiServiceErrorImpl implements HiService {
    @Override
    public String hi() {
        return "出现一个错误!";
    }

    @Override
    public String hiFeign(String name) {
        return "HiFeign调用失败了......";
    }
}
