package com.janson.springboot.lab12.myabtis.dataobject;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 用户DO
 * @Author: shanjian
 * @Date: 2023/12/28 20:27
 */
@Data
@Builder
public class UserDO {

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 账号
     */
    private String username;

    private String password;

    private Date createTime;
}
