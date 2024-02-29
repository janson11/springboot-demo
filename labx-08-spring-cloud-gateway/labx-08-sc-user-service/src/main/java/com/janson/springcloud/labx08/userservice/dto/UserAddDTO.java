package com.janson.springcloud.labx08.userservice.dto;

import java.io.Serializable;

/**
 * @Description: 用户添加DTO
 * @Author: shanjian
 * @Date: 2024/2/28 17:26
 */
public class UserAddDTO implements Serializable {
    /**
     * 昵称
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
