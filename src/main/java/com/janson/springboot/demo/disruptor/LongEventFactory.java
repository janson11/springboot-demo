package com.janson.springboot.demo.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Description: 事件生产工厂：生产事件存入ringBuffer中
 * @Author: Janson
 * @Date: 2023/5/11 22:51
 **/
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
