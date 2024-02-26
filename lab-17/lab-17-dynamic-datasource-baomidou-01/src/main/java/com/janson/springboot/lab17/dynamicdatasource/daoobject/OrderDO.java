package com.janson.springboot.lab17.dynamicdatasource.daoobject;

import lombok.Data;

/**
 * @Description: 订单DO
 * @Author: shanjian
 * @Date: 2023/12/28 20:21
 */
@Data
public class OrderDO {

    /**
     * 订单编号
     */
    private Integer id;


    /**
     * 用户编号
     */
    private Integer userId;
}
