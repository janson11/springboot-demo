package com.janson.service.impl;

import com.janson.service.MyInterface;

/**
 * @Description: 实现了 接口MyInterface 和接口的 play()方法，可以作为被代理类
 * @Author: Janson
 * @Date: 2024/12/19 15:41
 **/
public class TargetObject  implements MyInterface {
    @Override
    public void play() {
        System.out.println("妲己,陪你玩~");
    }
}
