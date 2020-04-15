package com.lee.springcloud.controller;

import com.lee.springcloud.model.User;
import com.lee.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @date 2020/4/15 21:44
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public User user(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }

}
