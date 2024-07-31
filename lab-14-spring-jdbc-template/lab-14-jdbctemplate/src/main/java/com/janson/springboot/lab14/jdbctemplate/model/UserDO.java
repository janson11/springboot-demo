package com.janson.springboot.lab14.jdbctemplate.model;

import java.util.Date;

/**
 * @Description: 用户DO
 * @Author: shanjian
 * @Date: 2024/7/19 17:03
 */
public class UserDO {

    private Integer id;



    private String username;

    private String password;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public UserDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDO setUsername(String name) {
        this.username = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
