package com.lee.springcloud.service;

import com.lee.springcloud.model.User;

/**
 * @author lee
 * @date 2020/4/15 18:07
 */
public interface CacheService {
    String getMessageById(User user);
}
