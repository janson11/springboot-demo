package com.janson.algorithm.basic;

import java.util.Stack;

/**
 * @Description: 如何仅使用栈结构实现队列结构
 * @Author: shanjian
 * @Date: 2022/9/22 10:04 上午
 */
public class MyStack2 {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public MyStack2() {
        this.stackPush = new Stack<Integer>();
        this.stackPop = new Stack<Integer>();
    }

    public void push(int newNum) {
//        if (this.stackPop.isEmpty()) {
//            this.stackPop.push(newNum);
//        } else if (newNum<this.getMin()){
//            this.stackPop.push(newNum);
//        } else {
//            int newMin = this.stackPop.peek();
//            this.stackPop.push(newMin);
//        }
        this.stackPush.push(newNum);
        dao();

    }


    public int poll() {
        if (this.stackPop.isEmpty() && this.stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        dao();
        return this.stackPop.pop();
    }

    public int pop() {
        if (this.stackPush.isEmpty()) {
            throw new RuntimeException("Your stack is empty.");
        }
        this.stackPop.pop();
        return this.stackPush.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty!");
        }
        dao();
        return stackPop.peek();
    }

    public void dao() {
        if (!stackPop.isEmpty()) {
            return;
        }
        while (!stackPush.isEmpty()) {
            stackPop.push(stackPush.pop());
        }
    }

    private int getMin() {
        if (this.stackPop.isEmpty()) {
            throw new RuntimeException("Your stack is empty.");
        }
        return this.stackPop.peek();
    }



}
