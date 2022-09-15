package com.janson.algorithm.basic;

/**
 * @Description: 用数组结构实现大小固定的队列和栈【大厂一面算法题】
 * @Author: shanjian
 * @Date: 2022/9/15 9:48 上午
 */
public class ArrayStack {
    private Integer[] arr;
    private Integer size;

    public ArrayStack(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("The init size is less than 0");
        }
        arr = new Integer[initSize];
        size = 0;
    }

    // -----------------用数组结构实现大小固定的栈 start---------------------


    /**
     * 获取栈顶的元素，返回元素但是不抹除元素
     *
     * @return
     */
    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return arr[size - 1];
    }

    /**
     * 压栈
     *
     * @param obj
     */
    public void push(int obj) {
        if (size == arr.length) {
            throw new ArrayIndexOutOfBoundsException("The queue is full");
        }
        arr[size++] = obj;
    }

    /**
     * 出栈
     *
     * @return
     */
    public Integer pop() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("The queue is empty");
        }
        return arr[--size];
    }

    // -----------------用数组结构实现大小固定的栈 end-----------------------


    // -----------------用数组结构实现大小固定的队列 start--------------------

    public static class ArrayQueue {
        private Integer[] arr;
        private Integer size;
        private Integer start;
        private Integer end;

        public ArrayQueue(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            size = 0;
            start = 0;
            end = 0;
        }

        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[start];
        }

        public void push(int obj) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }
            size++;
            arr[end] = obj;
            end = end == arr.length - 1 ? 0 : end + 1;
        }

        public Integer poll() {
            if (size ==0) {
                throw new ArrayIndexOutOfBoundsException("The queue is empty");
            }
            size--;
            int tmp = start;
            start =start ==arr.length-1?0:start+1;
            return arr[tmp];
        }

    }


    // -----------------用数组结构实现大小固定的队列 end----------------------
}
