package com.janson.lab00.spring.bean;

/**
 * @Description: 部门类
 * @Author: Janson
 * @Date: 2024/12/25 8:47
 **/
public class Dept {

    private String dname;

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                '}';
    }
}
