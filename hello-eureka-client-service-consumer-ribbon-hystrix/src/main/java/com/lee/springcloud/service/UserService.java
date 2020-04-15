package com.lee.springcloud.service;

import com.lee.springcloud.model.User;

import java.util.List;

/**
 * @author lee
 * @date 2020/4/15 21:18
 */
public interface UserService {
    User findOne(Long id);

    List<User> findAll(List<Long> ids);
}
