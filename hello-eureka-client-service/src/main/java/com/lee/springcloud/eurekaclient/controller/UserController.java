package com.lee.springcloud.eurekaclient.controller;

import com.lee.springcloud.eurekaclient.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 2020/4/15 21:12
 */
@RestController
public class UserController {
    @PostMapping("/user")
    public User user(@RequestBody Long id) {
        System.out.println("=======================哎呀,单独的请求被调用了");
        return new User(id, "user---" + System.currentTimeMillis());
    }

    @PostMapping("/users")
    public List<User> users(@RequestBody List<Long> ids) {
        System.out.println("=======================哎呀,请求合并被调用了");
        List<User> result = new ArrayList<>();
        ids.forEach(id -> {
            result.add(new User(id, "users###" + System.currentTimeMillis()));
        });

        return result;
    }
}
