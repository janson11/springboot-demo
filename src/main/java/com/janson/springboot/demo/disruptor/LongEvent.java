package com.janson.springboot.demo.disruptor;

import lombok.Data;

/**
 * @Description:  向ringBuffer中插入的事件元素：就是在对象中放了一个long变量
 * @Author: Janson
 * @Date: 2023/5/11 22:48
 **/
@Data
public class LongEvent {

    private long value;
}
