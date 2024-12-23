package com.janson.lab00.spring.model;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/22 23:01
 **/
public class Orders {

    private String oname;

    private String address;

    // 有参数构造
    public Orders(String oname, String address) {
        this.oname = oname;
        this.address = address;
    }

    public void orderTest() {
        System.out.println("订单信息：" + oname + " " + address);
    }
}
