package com.janson.springcloud.labx03.feigndemo.consumer.dto;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/29 16:25
 **/
public class DemoDTO {

    private String username;
    private String password;

    public DemoDTO(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public DemoDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DemoDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
