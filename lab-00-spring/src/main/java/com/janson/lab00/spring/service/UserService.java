package com.janson.lab00.spring.service;

import com.janson.lab00.spring.dao.UserDao;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/24 8:56
 **/
public class UserService {

    // 创建UserDao类型属性，生成set方法
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service add ...............");
        userDao.update();
    }
}