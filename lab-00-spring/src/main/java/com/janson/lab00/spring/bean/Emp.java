package com.janson.lab00.spring.bean;

/**
 * @Description: 员工类
 * @Author: Janson
 * @Date: 2024/12/25 8:48
 **/
public class Emp {
    private String ename;

    private String gender;

    // 员工属于某一个部门，使用对象表示
    private Dept dept;
    // 生成dept的get方法

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void add() {
        System.out.println("添加员工：" + this.ename + "，性别：" + this.gender + "，部门：" + this.dept);
    }
}
