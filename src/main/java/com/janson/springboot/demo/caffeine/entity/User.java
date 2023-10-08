package com.janson.springboot.demo.caffeine.entity;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/9/4 7:44 PM
 */

public class User {

    private Long userId;

    private String username;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
