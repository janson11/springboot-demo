package com.janson.lab00.spring.model;

/**
 * @Description: 演示使用set方法进行注入属性
 * @Author: Janson
 * @Date: 2024/12/22 22:46
 **/
public class Book {

    // 创建属性
    private String bname;

    private String bauthor;

    private String address;

    // set方法注入
    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void testDemo() {
        System.out.println(bname + ":" + bauthor + ":" + address);
    }

    public static void main(String[] args) {
        Book book = new Book();
        book.setBname("Java从入门到放弃");
        book.setBauthor("Janson");
    }
}
