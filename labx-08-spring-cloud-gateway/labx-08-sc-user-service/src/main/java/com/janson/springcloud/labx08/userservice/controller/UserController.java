package com.janson.springcloud.labx08.userservice.controller;

import com.janson.springcloud.labx08.userservice.dto.UserAddDTO;
import com.janson.springcloud.labx08.userservice.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/2/28 17:21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 -男； 2-女
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        // 嘿嘿，随便返回一个 id
        return (int) System.currentTimeMillis() / 1000;
    }
}
