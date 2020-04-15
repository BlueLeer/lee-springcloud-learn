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
    @GetMapping("/user")
    public User user(Long id) {
        System.out.println("=======================哎呀,单独的请求被调用了");
        return new User(id, "user---" + System.currentTimeMillis());
    }

    @GetMapping("/users")
    public List<User> users(String ids) {
        System.out.println("=======================哎呀,请求合并被调用了");
        List<User> result = new ArrayList<>();
        String[] split = ids.split(",");
        List<String> ids2 = Arrays.asList(split);
        ids2.forEach(id -> {
            result.add(new User(Long.valueOf(id), "users###" + System.currentTimeMillis()));
        });

        return result;
    }
}
