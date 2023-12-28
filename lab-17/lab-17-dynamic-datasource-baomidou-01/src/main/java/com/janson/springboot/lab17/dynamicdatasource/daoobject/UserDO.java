package com.janson.springboot.lab17.dynamicdatasource.daoobject;

import lombok.Data;

/**
 * @Description: 用户DO
 * @Author: shanjian
 * @Date: 2023/12/28 20:27
 */
@Data
public class UserDO {

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 账号
     */
    private String username;
}
