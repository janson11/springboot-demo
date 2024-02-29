package com.janson.springcloud.labx08.userservice.dto;

import java.io.Serializable;

/**
 * @Description: 用户信息DTO
 * @Author: shanjian
 * @Date: 2024/2/28 17:23
 */
public class UserDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public UserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
}
