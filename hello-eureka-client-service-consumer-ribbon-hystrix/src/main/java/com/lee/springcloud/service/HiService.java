package com.lee.springcloud.service;

import java.util.concurrent.Future;

/**
 * @author WangLe
 * @date 2019/7/17 18:19
 * @description
 */
public interface HiService {

    String hi();
    Future<String> hi2();
    Future<String> hi3();
    String hi4();
}
