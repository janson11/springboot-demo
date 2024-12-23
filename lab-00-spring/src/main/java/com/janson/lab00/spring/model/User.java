package com.janson.lab00.spring.model;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/22 17:00
 **/
public class User {

    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public void add() {
        System.out.println("add user......");
    }

}
