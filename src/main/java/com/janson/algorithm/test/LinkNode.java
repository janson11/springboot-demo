package com.janson.algorithm.test;

/**
 * @Description: 单链表
 * 结构： |data|next|
 * @Author: shanjian
 * @Date: 2022/2/21 10:56 上午
 */
public class LinkNode {


    /**
     * 数据域:存储数据元素信息的域称为数据域
     */
    private int data;
    // 指针域
    private LinkNode next;

    public LinkNode() {
    }

    public LinkNode(int data, LinkNode next) {
        this.data = data;
        this.next = next;
    }

    public int getVal() {
        return data;
    }

    public void setVal(int data) {
        this.data = data;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
