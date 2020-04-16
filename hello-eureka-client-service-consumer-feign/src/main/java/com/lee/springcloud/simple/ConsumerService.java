package com.lee.springcloud.simple;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Feign继承的特性
 *
 * @author lee
 * @date 2020/4/16 16:35
 */
@FeignClient("eureka-client-service")
public interface ConsumerService extends CommonService {
}
